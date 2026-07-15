public interface IManager<T> {
    void Add(T Object);
    void Update(String MA, T NewObject);
    void Delete(String MA);
    void ShowAll();
}
