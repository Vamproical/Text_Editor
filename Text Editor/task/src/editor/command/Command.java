package editor.command;

abstract class Command implements Runnable {

    abstract public void execute();

    @Override
    public void run() {
        this.execute();
    }
}
