/*
 *  DSched.java -- disk I/O scheduler code.
 *  <<<INSERT YOUR NAME AND EMAIL HERE>>>
 *  Akash Payne Ap567@kent.ac.uk
 */

/*
 *  FIXME: this implements a FIFO (first in, first out) request queue.
 *  requests are collected in the "readqueue" array, which acts as a circular
 *  buffer, with "readqueue_head" pointing at the next empty slot, "readqueue_tail"
 *  pointing at the oldest request slot, and "readqueue_size" indicating how many
 *  entries are in the buffer.
 *
 *  This is *not* an efficient way of doing disk scheduling because the time taken
 *  to move the read heads between tracks is significant.
 *
 *  Three methods are defined in the disk simulator code which you can call in addition
 *  to the ones already used:
 *
 *      int DiskSim.block_to_head (int blk)
 *      int DiskSim.block_to_track (int blk)
 *      int DiskSim.block_to_sector (int blk)
 *
 *  Which return respectively the head, track and sector for a particular block.
 */

/*
 *  NOTE: for the Java version, you may *not* use collection classes such as ArrayList here.
 *  Only primitive arrays are allowed.  If you want some sorting, you'll have to do that
 *  yourself.  The reasoning is that code inside an OS does not normally have access to a
 *  rich API of things that Java might normally offer.
 *
 *  If you have queries about what you can/cannot use, please ask on the anonymous Q+A forum,
 *  linked from the module's Moodle page.
 */

public class DSched
{
    /*{{{  private class ReadReq*/
    /*
     *  local class/structure to hold read requests
     */
    private class ReadReq
    {
        int blk;        /* block number */
        BRequest req;       /* request info */
        int track; /* track # */

        /* constructor: just clear fields */
        public ReadReq ()
        {
            blk = -1;
            track = -1; /* set track */
            req = null;
        }
    }
    /*}}}*/

    /* Private state */
    private ReadReq readqueue[];
    private int readqueue_head;
    private int readqueue_tail;
    private int readqueue_size;
    
    /* boolean state variables */
    private boolean moveDown; 


    /*{{{  public DSched ()*/
    /*
     *  Constructor, initialises various local state.
     */
    public DSched ()
    {
        readqueue = new ReadReq[DiskSim.MAXREQUESTS];
        readqueue_head = 0;
        readqueue_tail = 0;
        readqueue_size = 0;
        moveDown = true;

        /* allocate individual ReadReq entries */
        for (int i=0; i<readqueue.length; i++) {
            readqueue[i] = new ReadReq ();
        }
    }
    /*}}}*/

    /*{{{  public void blockread (int blk, BRequest req)*/
    /*
     *  Called by higher-level code to request that a block is read.
     */
    public void blockread (int blk, BRequest req)
    {
        /* add the request to the head of the queue */
        readqueue[readqueue_head].blk = blk;
        readqueue[readqueue_head].req = req;

		/* add the track to the head of the queue */
		readqueue[readqueue_head].track = (int) (blk/DiskSim.NSECTORSPERTRACK) % (DiskSim.NTRACKS);
		
		/* sort the array */
		sortarr();
        //readqueue = sort (readqueue); 
		
        /* increment head pointer (modulo buffer size) */
        readqueue_head = (readqueue_head + 1) % DiskSim.MAXREQUESTS;
        readqueue_size++;
        
       
    }
    /*}}}*/
    /*{{{  public void readcomplete (int blk, BRequest req)*/
    /*
     *  Called by lower-level (disk) code when a read has completed and the next
     *  read can be dispatched.  If "blk" < 0 or "req" == null, then this is telling
     *  us that the disk is idle and ready to accept a new read request.
     */
    public void readcomplete (int blk, BRequest req)
    {
        if (blk >= 0) {
            /* give the block read back to the high-level system */
            DiskSim.highlevel_didread (blk, req);
        }

        if (readqueue_size > 0) {
            /* still got requests to service, dispatch the next block request (at tail) */
            DiskSim.disk_readblock (readqueue[readqueue_tail].blk, readqueue[readqueue_tail].req);
             
            /*
             * // if go down -> get the highest block below blk
             * if (moveDown) {
             *     int index
             *     for (int i = 0; i < readqueue_size; i++) {
             *         
             *     }
             *  }
             *  // otherwise get smallest higher block
             *  
             */
            
            /* increment t  ail pointer, modulo buffer size */
            readqueue_tail = (readqueue_tail + 1) % DiskSim.MAXREQUESTS;
            readqueue_size--;
        }
    }
    /*}}}*/
	
	/*
	 * Called by the blockread method, and will sort the readqueue array
	 *
	 */
    public void sortarr()
	{
		if (!moveDown) {
			moveDown = true; 
			for ( int i = readqueue_tail; i != readqueue_head; i = ((i+1) % DiskSim.MAXREQUESTS) )
			{
				int x = ((i + 1) % DiskSim.MAXREQUESTS);
				if (readqueue[i].track > readqueue[x].track) {
					ReadReq storeData = readqueue[i];
					readqueue[i] = readqueue[x];
					readqueue[x] = storeData;
					moveDown = false;
				}
			}
			
			if (!moveDown) {
				sortarr();
			}
		}
	}
	
	
	
    /*
     *  Called by the blockRead code when a request for a block to be read by higher level code. 
     *  This sorts the array to allow the disk scheduling to be passed quickier. 
	 *
     */
    private ReadReq[] sort( ReadReq[] queue ) {
        int highestValueAdded = -1;
        int sortedArraySize = 0; 
        ReadReq[] sortedArray = new ReadReq[queue.length];
        
        while (sortedArraySize < readqueue_size) {
            int lowestValue = Integer.MAX_VALUE;
        
            // Finds the value of the < value in the array 
            // (that is > the highest value added)
            for (int i = 0; i < readqueue_size; i++) {
                if (queue[i].blk == lowestValue) {
                    sortedArray[sortedArraySize] = queue[i];
                    sortedArraySize++;
                }
            }
            highestValueAdded = lowestValue;
        }
        for (int i = sortedArraySize; i < sortedArray.length; i++ ) {
            sortedArray[i] = queue[i];
        }
        return sortedArray;
    }
}