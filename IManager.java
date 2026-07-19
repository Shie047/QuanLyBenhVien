public interface IManager<T> {
    void add(T Object);
    void update(String MA, T NewObject);
    void delete(String MA);
    void showAll();
}