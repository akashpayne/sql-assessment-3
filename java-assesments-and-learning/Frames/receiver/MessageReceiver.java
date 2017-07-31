// MessageReceiver.java (partial implementation)

/**
 * This class implements the receiver side of the data link layer.
 * <P>
 * The source code supplied here contains only a partial implementation.
 * Your completed version must be submitted for assessment.
 * <P>
 * You only need to finish the implementation of the receiveMessage
 * method to complete this class.  No other parts of this file need to
 * be changed.  Do NOT alter the constructor or interface of any public
 * method.  Do NOT put this class inside a package.  You may add new
 * private methods, if you wish, but do NOT create any new classes. 
 * Only this file will be processed when your work is marked.
 */

public class MessageReceiver
{
    // Fields ----------------------------------------------------------

    private FrameReceiver physicalLayer; // physical layer object
    private boolean quiet;               // true=quiet mode (suppress
                                         // prompts and status info)

    // You may add additional fields but this shouldn't be necessary

    // Constructor -----------------------------------------------------

    /**
     * MessageReceiver constructor (DO NOT ALTER ANY PART OF THIS)
     * Create and initialize new MessageReceiver.
     * @param physicalLayer physical layer object with frame receiver service
     * (this will already have been created and initialized by TestReceiver)
     * @param quiet true for quiet mode which suppresses prompts and status info
     */

    public MessageReceiver(FrameReceiver physicalLayer, boolean quiet)
    {
        // Initialize fields and report status

        this.physicalLayer = physicalLayer;
        this.quiet = quiet;
        if (!quiet) {
            System.out.println("Data link layer ready");
        }
    }

    // Methods ---------------------------------------------------------

    /**
     * Receive a message (THIS IS THE ONLY METHOD YOU NEED TO MODIFY)
     * @return the message received, or null if the end of the input
     * stream has been reached.  (See receiveFrame documentation for
     * further explanation of how the end of the input stream is
     * detected and handled.)
     * @throws ProtocolException immediately without attempting to
     * receive any further frames if any error is detected, such as
     * a corrupt frame being received
     */
    public String receiveMessage() throws ProtocolException
    {
        // The following block of statements shows how the frame receiver
        // is invoked.  At the moment it just returns the frame directly
        // to the application layer.  That is, of course, incorrect!

        // receiveMessage should invoke receiveFrame one or more times
        // to obtain all of the frames that make up a message, extract
        // the message segments and join them together to recreate the
        // original message string.  The whole message is processed by
        // a single execution of receiveMessage.  See the coursework
        // specification and other class documentation for further info.
        // Note the System.out.println statement isn't required by the
        // protocol, it's there just to help when debugging

        String message = ""; 
        String frame = physicalLayer.receiveFrame();
        boolean last = false;  
        // boolean last: used in while loop ( checks if frame is the last one charAt(1) )  
        
        // if (!quiet) {
        //         System.out.println("1:Frame received => " + frame);  
        // } 
        if (frame == null) {
            return frame; 
        } else {
            while(!last) {
                // errorMessage Carries validation feedback and runs exception if found
                String errorMessage = validateFrame(frame);
                if(errorMessage != null) {
                    throw new ProtocolException(
                        "Invalid " + errorMessage + "please retry."
                    ); 
                }
                
                // checks what type of frame the given frame is
                char frameType = frame.charAt(1);
                // System.out.println(frameType);  // debug (type of frame)
                if( frameType == 'D') {
                    message = message + splitMessage(frame); // splits frame and adds it to message: "" + "Hi"  
                    frame = physicalLayer.receiveFrame(); // recieve next frame
                } else if (frameType == 'E') {
                    // split message and add to end of frame. Set last to true 
                    message = message + splitMessage(frame);
                    last = true;
                    return message;
                }
                /* if (!quiet) { 
                    System.out.println("Message formed => " + message); System.out.println("Frame left   => " + frame); 
                } 
                */
            }
        }
        return message; 
    } // end of method receiveMessage

    // You may add private methods if you wish
    
    /**
    * Checks a frame to ensure that it is within valid structure, message size and that the checksum is correct.
    * @param frame to be validated
    * @return the errorMessages that frame has
    */
    private String validateFrame(String frame)
    {   
        // checks if the frame is in the correct format
        String errorMessage = "";
        if(!frame.matches("<[E|D]-\\d{2}-.{1,99}-\\d{2}>")) { errorMessage += "frame format, "; }
        else {
            // checks if the frame size is less than the MTU else error. 
            int mtu = physicalLayer.getMTU();
            if( frame.length() > mtu ) { errorMessage += "frame size exceeds the MTU, "; }
            
            // checks if the message is of the correct length. 
            int validLength = splitMessage(frame).length();
            int frameLength = Integer.parseInt(frame.substring(3, 5));
            if( validLength != frameLength ) { errorMessage += "message size counter, "; }
            
            // checks if the check sum is correct (compares with frame checksum).
            String correctCheckSum = generateCheckSum(frame.substring(0, (frame.length() - 3)));
            String frameCheckSum = frame.substring((frame.length() - 3), (frame.length() - 1));
            if( !correctCheckSum.equals(frameCheckSum) ) { errorMessage += "current checksum value, "; }
        }
        
        // checks error message, if null return null else return message
        if(errorMessage.length() == 0) { return null; } 
        else { return errorMessage; }
    }
    
    /**
    * Takes a valid frame and returns the message that is split from it. 
    * @param frame to extract a message from
    * @return frame message
    */
    private String splitMessage(String frame)
    {
        // splits the frame apart to gain the message inside "<a-00-message-99>"
        String message = frame.substring(6, (frame.length() - 4));
        return message;
    }
    
    /**
     * Takes a frame and calculates the check sum value by the arithmetic 
     * sum of the characters inside the frame, (same as sender class).
     * @param frame the frame which is having it's check sum calculated
     * @return the checkSum value of the frame
     */
    private String generateCheckSum(String frame)
    {
        int checkSum = 0;
		//System.out.println("frame: " + frame);
        for(int i = 1; i < frame.length(); i++) {
            checkSum = checkSum + frame.charAt(i);
        }
        String csString = Integer.toString(checkSum);
        String csString2 = csString.substring((csString.length() - 2), csString.length()); 
		// csString2 for debug (checking conversion)
        return csString2;
    }
} // end of class MessageReceiver

