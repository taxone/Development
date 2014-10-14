package it.kion;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;

import com.sun.management.*;

public class MXBeanPrinter {

	public static void main(String[] args) {
		OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
		
		System.out.println("Os Version: "+osBean.getVersion());
		
		com.sun.management.OperatingSystemMXBean sunBean = (com.sun.management.OperatingSystemMXBean) osBean;
		
		RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
		String specVersion = runtimeMXBean.getSpecVersion();
		
		System.out.println("VM info: "+runtimeMXBean.getVmVersion());
		
		System.out.println("TotalPhysicalMemorySize: "+sunBean.getTotalPhysicalMemorySize() / 1000000+" MB");
		System.out.println("FreePhysicalMemorySize: "+sunBean.getFreePhysicalMemorySize() / 1000000+" MB");
		
		System.out.println("Processori: "+osBean.getAvailableProcessors());
	}
}
