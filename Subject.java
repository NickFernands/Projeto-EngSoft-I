public interface Subject {
    void registerObserver(Observers o);
    void removeObserver(Observers o);
    void notifyObservers();
}
