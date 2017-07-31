// FrameReceiver.java (full implementation)
// DO NOT CHANGE THIS CLASS

import java.io.*;

/**
 * This class implements the receiver side of the physical layer.
 * <P>
 * The source code supplied is already complete.  It does NOT
 * need to be changed and will NOT be submitted for assessment.
 * You may, if you wish, alter the methods in this class for the
 * purpose of debugging your implementation of MessageReceiver,
 * but be careful not to change the interface.
 * <P>
 * In this implementation of the class, frames are read from
 * standard input (normally the keyboard).  Each line of input is
 * treated as a separate frame.  See comments in receiveFrame
 * method regarding detection of the end of the input stream.
 */

public class FrameReceiver
{
    // Fields ----------------------------------------------------------

    private int mtu;              // maximum transfer unit (frame length limit)
    private boolean quiet;        // true=quiet mode (suppress prompts and status info)
    private BufferedReader in;    // input stream from which frames are read
    private String stop;          // user enters this to quit (null = disable)

    // Constructor -----------------------------------------------------

    /**
     * FrameReceiver constructor.
     * Create and initialize new FrameReceiver.
     * @param mtu the maximum transfer unit (frame length) to support
     * @param quiet true for quiet mode which suppresses prompts and status info
     * @param stop string that will signal the end of the input stream (null = none)
     */

    public FrameReceiver(int mtu, boolean quiet, String stop)
    {
        // Initialize fields
        // Frames will be read from standard input (normally the keyboard)

        this.mtu = mtu;
        this.quiet = quiet;
        this.stop = stop;
        this.in = new BufferedReader(new InputStreamReader(System.in));

        // Report status

        if (!quiet) {
            System.out.println("Physical layer ready : mtu = " + mtu);
        }
    }

    // Methods ---------------------------------------------------------

    /**
     * Get the MTU
     * @return the maximum transfer unit supported
     */

    public int getMTU()
    {
        return mtu;
    }

    // -----------------------------------------------------------------

    /**
     * Receive a single frame.
     * <P>
     * @return a string containing the frame received, or null if the
     * end of the input stream has been reached.
     * <P>
     * The end of the input stream is detected when the user types "."
     * (see field <i>stop</i>) or the physical end of the file is reached
     * if input redirection is being used.  In either case this method
     * will exit immediately and return null.  The "." is NOT returned.
     * <P>
     * Note that in real systems noise may be present between frames.
     * The returned string could therefore include a few random characters
     * before and after the frame itself.  This would not generally be
     * treated as an error.
     * <P>
     * @throws ProtocolException in the event of an error
     */

    public String receiveFrame() throws ProtocolException
    {
        // Prompt for next frame

        if (!quiet) {
            System.out.print("Enter frame ....... ");
            System.out.flush();
        }

        // Read and return frame
        // readLine returns null if end of stream reached
        // End of stream signalled by string matching field stop
        // or actual physical end when input redirected from a file
        // Matching against stop disabled if stop contains null
        // Catch any exception

        try {
            String frame = in.readLine();
            if (frame == null || (stop != null && frame.equals(stop)))
                frame = null;
            return frame;
        }
        catch (Exception e) {
            throw new ProtocolException("readLine() failed : " + e.getMessage());
        }

    }

} // end of class FrameReceiver

