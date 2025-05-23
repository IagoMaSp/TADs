package um.edu.uy.Interfaces;

import java.util.List;

public interface MyBinaryTree<K,T> {
    T find(K key);

    void insert(K key, T data, K parentKey);
    void delete(K key);

    int size();
    int countLeaf();
    int countCompleteElements();

    MyList<K> inOrder();
    MyList<K> preOrder();
    MyList<K> postOrder();
    MyList<K> porNivel();
}
