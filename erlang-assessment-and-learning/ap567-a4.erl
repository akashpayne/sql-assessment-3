%
%  CO545 - Assessment 4
%  Dining Philosophers with Animation
%  Akash Payne ap567
%  Date : 25/03/2015
%  Version : 4.0
%	- updated to stop an error that crashed the program [abort and verbose].
%   - updated to prevent deadlock (does not let 5 left forks be grabbed at once) [validations]
%   - updated so the display sends a wait message to communicate the next step [display]
%   - updated so philosophers wait for the wait message when they are dining or thinking
%   - updated for left and right forks 
%

-module(display).
-export([start/0,display_function/0,philosopher/1]).

%%
%% constants
%%

-define(PHILOSOPHERS,5). %% num of philosophers
-define(WAIT_COUNT,1000). %% wait time
-define(VERBOSE_STAGE,2). %% higher for more verbose

start() ->
    random:seed( now() ), %% start the display
		register( %% spin the philosophers 
			display_atom, 
			spawn( display, %% link to the display 
			display_function, %% & wait for count
			[] ) ),  
		spawn_philosopher( 0 ),
		display_atom ! go. %% let the display start counting

spawn_philosopher( ?PHILOSOPHERS ) -> ok;
	
	spawn_philosopher( Index ) ->
		PhilosopherName = list_to_atom( lists:flatten( io_lib:format( "philo~b", [ Index ] ) ) ),
		verbose( 1, "Starting ~p~n", [ PhilosopherName ] ),
		register( PhilosopherName, spawn( display, philosopher, [ Index ] ) ),
		spawn_philosopher( Index + 1 ).

%% verbose: causes more verbose info from the compiler describing what it is doing. 
verbose( Stage, Format, Args ) when Stage =< ?VERBOSE_STAGE ->io:format( Format, Args );
	verbose( _, _, _ ) -> ok.		
		
display_function() ->
    receive
        go -> display_count_loop(0,0);
        die -> exit( shutdown ) %% will kill the child too
    end.

display_count_loop( _, ?WAIT_COUNT ) -> verbose( 1, "end~n", [] ),
    exit( shutdown ); 
	
	%% pick which philosopher will wait
	display_count_loop( BusyForks, TickCount ) ->    
		RandomPhilosopher = list_to_atom( lists:flatten( 
			io_lib:format( "philo~b", [ random:uniform( ?PHILOSOPHERS ) - 1 ] ) ) ),
				verbose( 2, "= wait ~p: ~p~n", [ TickCount, RandomPhilosopher ] ), % verbose 
				verify_philosopher( 0 ), %% verify 
				%% abort if an impossible state is detected	
				RandomPhilosopher ! wait, %% send the wait
				display_receive_loop( BusyForks, TickCount ).

%% Each sequence of wait can lead to multiple requests,
%%% > processes all messages between the waits
display_receive_loop( BusyForks, TickCount ) ->
    receive
        { grab_left_fork, Pid, Index } ->
            verbose( 3, "display: ~p wants left fork~n", [ Index ] ),
            NewBusyForks = display_check_fork( left, Pid, Index, BusyForks ),
            display_receive_loop( NewBusyForks, TickCount );
        
		{ grab_right_fork, Pid, Index } ->
            verbose( 3, "display: ~p wants right fork~n", [ Index ] ),
            NewBusyForks = display_check_fork( right, Pid, Index, BusyForks ),
            display_receive_loop( NewBusyForks, TickCount );
        
		{ done_eating, _, Index } ->
            verbose( 3, "display: ~p is done eating~n", [ Index ] ),
            display_receive_loop( BusyForks - 2, TickCount );
        
		die -> exit( shutdown )
    after
        0 ->
            verbose( 3, "No messages, please wait again~n", [] ),
            display_count_loop( BusyForks, TickCount + 1 )
    end.

%% left fork is picked up first  never allow the last one to be grabbed
display_check_fork( left, Pid, _, ?PHILOSOPHERS - 1 ) ->
    Pid ! deny_fork,
    ?PHILOSOPHERS - 1;

%% left philosopher
display_check_fork( left, Pid, Index, BusyForks ) ->	
	NIndex = ( Index + ?PHILOSOPHERS - 1 ) rem ?PHILOSOPHERS,
	NAtom = list_to_atom( lists:flatten( io_lib:format( "philo~b", [ NIndex ] ) ) ),
	verbose( 3, "ask ~p about forks~n", [ NAtom ] ),
	NPid = whereis( NAtom ),
	NPid ! { get_fork_state, self() },
	
    receive
        { no_fork, SenderPid } when SenderPid == NPid ->            
            Pid ! ok_fork,
            BusyForks + 1;
			
        { left_fork, SenderPid } when SenderPid == NPid ->
            Pid ! ok_fork,
            BusyForks + 1;
			
        { both_forks, SenderPid } when SenderPid == NPid ->
            Pid ! deny_fork,
            BusyForks
    end;
	
%% right philosopher	
display_check_fork( right, Pid, Index, BusyForks ) ->
    NIndex = ( Index + 1 ) rem ?PHILOSOPHERS,
    NAtom = list_to_atom( lists:flatten( io_lib:format( "philo~b", [ NIndex ] ) ) ),
    verbose( 3, "ask ~p about forks~n", [ NAtom ] ),
    NPid = whereis( NAtom ),
    NPid ! { get_fork_state, self() },
    receive
        { no_fork, SenderPid } when SenderPid == NPid ->
            Pid ! ok_fork,
            BusyForks + 1;
			
        { left_fork, SenderPid } when SenderPid == NPid ->
            Pid ! deny_fork,
            BusyForks;
			
        { both_forks, SenderPid } when SenderPid == NPid ->
            Pid ! deny_fork,
            BusyForks
    end.

%%
%% philosopher's process(es)
%%

philosopher( Index ) ->
    link( whereis( display_atom ) ),
    verbose( 3, "~p starts dining~n", [ Index ] ),
    philosopher_thinking( Index ).

philosopher_thinking( Index ) ->
    verbose( 3, "~p is thinking~n", [ Index ] ),
    philosopher_wait_tick( Index, no_fork ),
    philosopher_hungry( Index ).

philosopher_wait_tick( Index, ForkState ) ->
    receive
        { get_fork_state, Pid } ->
            philosopher_fork_reply( Index, Pid, ForkState ),
            philosopher_wait_tick( Index, ForkState );
        wait -> ok
    end.

philosopher_fork_reply( _, Pid, ForkState ) ->
    Pid ! { ForkState, self() }.

philosopher_hungry( Index ) ->
    verbose( 3, "~p is hungry~n", [ Index ] ),
    display_atom ! { grab_left_fork, self(), Index },
    philosopher_hungry_reply_loop( Index ).

philosopher_hungry_reply_loop( Index ) ->
    receive
        ok_fork ->            
            philosopher_hungry_left_fork( Index );
        deny_fork ->
            philosopher_wait_tick( Index, no_fork ),
            philosopher_hungry( Index );
        { get_fork_state, Pid } ->
            philosopher_fork_reply( Index, Pid, no_fork ),
            philosopher_hungry_reply_loop( Index )
    end.

%% if philosopher has left fork, they won't let go of it, and wants right fork now
philosopher_hungry_left_fork( Index ) ->
    verbose( 3, "~p is hungry and has a left fork~n", [ Index ] ),
    display_atom ! { grab_right_fork, self(), Index },
    philosopher_hungry_left_fork_reply_loop( Index ).

%% reply to any number of get_fork_state messages before continuing to the next state and then wait
philosopher_hungry_left_fork_reply_loop( Index ) ->
    receive
        ok_fork ->
            philosopher_eating( Index );
			
        deny_fork ->
            philosopher_wait_tick( Index, left_fork ),
            philosopher_hungry_left_fork( Index );
        
		{ get_fork_state, Pid } ->
            philosopher_fork_reply( Index, Pid, left_fork ),
            philosopher_hungry_left_fork_reply_loop( Index )
    end.

philosopher_eating( Index ) ->
    verbose( 3, "~p is eating~n", [ Index ] ),
    philosopher_wait_tick( Index, both_forks ),
    display_atom ! { done_eating, self(), Index },
    philosopher_thinking( Index ).

%%
%% state validations
%%

%% state cannot change:
%% only works because of the centralised display approach
verify_philosopher( ?PHILOSOPHERS ) ->
    ok;
	
verify_philosopher( Index ) ->
    Atom = list_to_atom( lists:flatten( io_lib:format( "philo~b", [ Index ] ) ) ),
    Pid = whereis( Atom ),
    Pid ! { get_fork_state, self() },
	
    receive
        %% nothing, go directly to the next philosopher
		{ no_fork, SenderPid } when SenderPid == Pid ->
            verbose( 2, "~p: no fork~n", [ Atom ] ),
            verify_philosopher( Index + 1 );
			
		%% verify that the philosopher on the left does not think he has both forks
        { left_fork, SenderPid } when SenderPid == Pid ->            
            verbose( 2, "~p: left fork~n", [ Atom ] ),
            assert_philosopher_state( ( Index + ?PHILOSOPHERS - 1 ) rem ?PHILOSOPHERS, { no_fork, left_fork } ),
            verify_philosopher( Index + 1 );
			
		%% verify that the philosopher on the right does not think he has left fork
        { both_forks, SenderPid } when SenderPid == Pid ->
            verbose( 2, "~p: both forks~n", [ Atom ] ),
            assert_philosopher_state( ( Index + ?PHILOSOPHERS - 1 ) rem ?PHILOSOPHERS, { no_fork, left_fork } ),
            assert_philosopher_state( ( Index + 1 ) rem ?PHILOSOPHERS, { no_fork } ),
            verify_philosopher( Index + 1 )
    end.

assert_philosopher_state( Index, AllowedStates ) ->
    NAtom = list_to_atom( lists:flatten( io_lib:format( "philo~b", [ Index ] ) ) ),
    Pid = whereis( NAtom ),
    Pid ! { get_fork_state, self() },
    receive
        { no_fork, SenderPid } when SenderPid == Pid ->
            test_philosopher_state( no_fork, AllowedStates );
			
        { left_fork, SenderPid } when SenderPid == Pid ->
            test_philosopher_state( left_fork, AllowedStates );
        
		{ both_forks, SenderPid } when SenderPid == Pid ->
            test_philosopher_state( both_forks, AllowedStates )
    end.

test_philosopher_state( State, AllowedStates ) ->
    ConditionsMet = lists:member( State, tuple_to_list( AllowedStates ) ),
    maybe_abort( not ConditionsMet, State, AllowedStates ).

maybe_abort( true, State, AllowedStates ) ->
    io:format( "Bad state, abort: ~p ~p~n", [ State, AllowedStates ] ),
    exit( shutdown ); % will kill the child too
	
maybe_abort( false, _, _ ) ->
    ok.
	
% References:	
%% String to atom:
%%% http://stackoverflow.com/questions/9322399/erlang-string-to-atom-and-formatting-a-string
%% the macros, i.e. ?philosopher
%%% http://www.erlang.org/doc/reference_manual/macros.html
%% Supervision:
%%% Learn You Some Erlang for Great Good!: A Beginner's Guide By Fred Hebert
%% testing:
%%% http://erlang.org/doc/man/eunit.html
%% errors 
%%% http://learnyousomeerlang.com/errors-and-exceptions
%% verbose 
%%% https://www.haskell.org/hoogle/?hoogle=verbose
%%% https://github.com/simplegeo/erlang/blob/master/lib/kernel/src/net_adm.erl