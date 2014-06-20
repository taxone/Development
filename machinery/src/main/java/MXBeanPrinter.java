import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

import com.sun.management.*;

public class MXBeanPrinter {

	public static void main(String[] args) {
		OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
		
		System.out.println("Os Version: "+osBean.getVersion());
		
		com.sun.management.OperatingSystemMXBean sunBean = (com.sun.management.OperatingSystemMXBean) osBean;
		
		System.out.println("TotalPhysicalMemorySize: "+sunBean.getTotalPhysicalMemorySize());
	}
}
