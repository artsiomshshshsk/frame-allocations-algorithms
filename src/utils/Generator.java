package utils;

import Components.Page;
import Components.Process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Generator {

    private final int NUMBER_OF_PAGES_MAX = 20;
    private int queriesNumber;
    private int processesNumber;
    private Random r = new Random();
    private ArrayList<Page> generatedPages = new ArrayList<>();
    private ArrayList<Process> processes = new ArrayList<>();
    private ArrayList<ArrayList<Page>> test= new ArrayList<>();

    public Generator(int queriesNumber, int processesNumber) {
        this.queriesNumber = queriesNumber;
        this.processesNumber = processesNumber;
    }


    public ArrayList<Page> getGeneratedPages(){
        ArrayList<Page> pool = new ArrayList<>();
        for(int i = 0; i < processesNumber;i++) {
            Process process = new Process();
            process.generatePages(r.nextInt(5, NUMBER_OF_PAGES_MAX));
            processes.add(process);
            for (Page page : process.getLogicalMemory()) {
                pool.add(page);
            }
        }

        int base = 15;
        int probability = 16;
        int start = r.nextInt(base, pool.size());

        for(int i = 0; i < queriesNumber;i++){
            if(r.nextInt(0,probability) == 4){
                start = r.nextInt(base + 1, pool.size() - (base+ 1));
            }
            int pos;
            if (r.nextBoolean()){
                pos = start - r.nextInt(0,base);
            }else {
                pos = start + r.nextInt(0,base);
            }
            generatedPages.add(pool.get(pos));
        }
        return generatedPages;
    }

    public ArrayList<Page> getGeneratedPages(boolean t){
        for(int i = 0; i < processesNumber;i++) {

            ArrayList<Page> perProcess = new ArrayList<>();
            Process process = new Process();
            process.generatePages(r.nextInt(5, NUMBER_OF_PAGES_MAX));
            processes.add(process);


            int base = 3;
            int probability = 16;
            int start = r.nextInt(0, process.getLogicalMemory().size() - base);

            for(int j = 0; j < queriesNumber;j++){
                if(r.nextInt(0,probability) == 4){
                    start = r.nextInt(0, process.getLogicalMemory().size() - base);
                }
                int pos = start + r.nextInt(0,base);
                perProcess.add(process.getLogicalMemory().get(pos));
            }
            test.add(perProcess);
        }

        while (testIsNotEmpty() && test.size() != 0){
            ArrayList<Page> pages = test.get(r.nextInt(0, test.size()));
            Page page = pages.remove(0);
            generatedPages.add(page);
            if(pages.size() == 0) test.remove(pages);
        }
        return generatedPages;
    }

    private boolean testIsNotEmpty(){
        for(ArrayList<Page> pages:test){
            if(pages.size() != 0){
                return true;
            }
        }
        return false;
    }




    public ArrayList<Process> getProcesses() {
        return processes;
    }
}
