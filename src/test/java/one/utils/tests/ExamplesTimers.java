package one.utils.tests;

import delight.concurrency.Concurrency;

import one.utils.jre.OneUtilsJre;

public class ExamplesTimers {

    /**
     * @param args
     */
    public static void main(final String[] args) {

        final Concurrency con = OneUtilsJre.newJreConcurrency();
        // ^-- replace with 'new GwtConcurrency()' for GWT environments
        // see https://gist.github.com/2791639

        // -----
        // Timer for one invocation
        // -----
        con.newTimer().scheduleOnce(200, new Runnable() {

            @Override
            public void run() {
                System.out.println("Do in 200 ms");
            }
        });

        // -----
        // Timer for multiple invocations
        // -----
        con.newTimer().scheduleRepeating(200, 100, new Runnable() {

            @Override
            public void run() {
                System.out.println("Do in 200 ms and then every 100 ms.");
            }
        });

    }

}
