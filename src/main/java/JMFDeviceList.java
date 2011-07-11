import java.util.Vector;

import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;


public class JMFDeviceList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 /////////////////////////////////////////////////////////////
	    // Query CaptureDeviceManager about ANY capture devices (null
	    // format)
	    Vector info = CaptureDeviceManager.getDeviceList(null);
	    if (info == null)
	      System.out.println("No Capture devices known to JMF");
	    else {
	      System.out.println("The following " + info.size()
	          + " capture devices are known to the JMF");
	      for (int i = 0; i < info.size(); i++)
	        System.out
	            .println("\t" + (CaptureDeviceInfo) info.elementAt(i));
	    }

	}

}
