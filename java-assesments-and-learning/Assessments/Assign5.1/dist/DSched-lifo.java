/*
 *	DSched.java -- disk I/O scheduler code.
 *	<<<INSERT YOUR NAME AND EMAIL HERE>>>
 */

/*
 *	FIXME: this implements a LIFO (last in, first out) request stack.
 *	requests are collected in the "readstack" array, acting as the stack.
 *	"readstack_size" indicates how many entries are there.
 *
 *	This is a particularly bad way of doing disk scheduling because the first
 *	request in will be the last processed (as long as there is more than 1
 *	request).
 *
 *	Three methods are defined in the disk simulator code which you can call in addition
 *	to the ones already used:
 *
 *		int DiskSim.block_to_head (int blk)
 *		int DiskSim.block_to_track (int blk)
 *		int DiskSim.block_to_sector (int blk)
 *
 *	Which return respectively the head, track and sector for a particular block.
 */

/*
 *	NOTE: for the Java version, you may *not* use collection classes such as ArrayList here.
 *	Only primitive arrays are allowed.  If you want some sorting, you'll have to do that
 *	yourself.  The reasoning is that code inside an OS does not normally have access to a
 *	rich API of things that Java might normally offer.
 *
 *	If you have queries about what you can/cannot use, please ask on the anonymous Q+A forum,
 *	linked from the module's Moodle page.
 */

public class DSched
{
	/*{{{  private class ReadReq*/
	/*
	 *	local class/structure to hold read requests
	 */
	private class ReadReq
	{
		int blk;		/* block number */
		BRequest req;		/* request info */

		/* constructor: just clear fields */
		public ReadReq ()
		{
			blk = -1;
			req = null;
		}
	}
	/*}}}*/

	/* Private state */
	private ReadReq readstack[];
	private int readstack_size;


	/*{{{  public DSched ()*/
	/*
	 *	Constructor, initialises various local state.
	 */
	public DSched ()
	{
		readstack = new ReadReq[DiskSim.MAXREQUESTS];
		readstack_size = 0;

		/* allocate individual ReadReq entries */
		for (int i=0; i<readstack.length; i++) {
			readstack[i] = new ReadReq ();
		}
	}
	/*}}}*/

	/*{{{  public void blockread (int blk, BRequest req)*/
	/*
	 *	Called by higher-level code to request that a block is read.
	 */
	public void blockread (int blk, BRequest req)
	{
		/* add the request to the stack */
		readstack[readstack_size].blk = blk;
		readstack[readstack_size].req = req;

		/* increment pointer */
		readstack_size++;
	}
	/*}}}*/
	/*{{{  public void readcomplete (int blk, BRequest req)*/
	/*
	 *	Called by lower-level (disk) code when a read has completed and the next
	 *	read can be dispatched.  If "blk" < 0 or "req" == null, then this is telling
	 *	us that the disk is idle and ready to accept a new read request.
	 */
	public void readcomplete (int blk, BRequest req)
	{
		if (blk >= 0) {
			/* give the block read back to the high-level system */
			DiskSim.highlevel_didread (blk, req);
		}

		if (readstack_size > 0) {
			/* still got requests to service, dispatch the request at the end */
			DiskSim.disk_readblock (readstack[readstack_size - 1].blk, readstack[readstack_size - 1].req);
			readstack_size--;
		}
	}
	/*}}}*/
}

