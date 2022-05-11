package Test;

import Allocation.EqualAllocation;
import Components.CPU;
import Components.Page;
import utils.Generator;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        System.out.println("___________________________________________");
        System.out.println("frame-allocations");
        Generator generator = new Generator(2000,10);
        ArrayList<Page> pageQueries = generator.getGeneratedPages();

        System.out.println("___________________________________________");
        System.out.println("Equal:");
        CPU cpu = new CPU(pageQueries,20,generator.getProcesses(),new EqualAllocation());
        cpu.execute();
        System.out.println("___________________________________________");
    }
}
