package org.example.introduction.collaboration1;

class JoinedThread extends Thread {

    private String name;
    private Object obj;

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public JoinedThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 100; i++) {
                System.out.println(name + i);
            }
        }
    }

}
