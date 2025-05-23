package um.edu.uy.Implementaciones;

import um.edu.uy.Exceptions.EmptyListException;
import um.edu.uy.Exceptions.ListOutOfIndex;
import um.edu.uy.Exceptions.ValueNoExist;
import um.edu.uy.Interfaces.MyList;

public class MyArrayListImpl<T> implements MyList<T> {
    private T[] mainArray;
    private int indexLastValue;

    public boolean isEmpty(){
        return this.indexLastValue == 0 && this.mainArray[0] == null;
    }

    public MyArrayListImpl(int size) {
        this.mainArray = (T[]) new Object[size];
        this.indexLastValue = 0;
    }

    @Override
    public void add(T data) {
        if (this.indexLastValue >= this.mainArray.length){
            incrementLenght();
        }
        this.mainArray[indexLastValue]=data;
        indexLastValue++;
    }

    @Override
    public void add(T data, int index) throws ListOutOfIndex {
        if (index > indexLastValue){
            throw new ListOutOfIndex("Maximo lugar " + this.indexLastValue);
        } else {
            if (this.indexLastValue >= this.mainArray.length){
                incrementLenght();
            }
            if (index==0){
                addFirst(data);
            }else if(index==this.indexLastValue){
                this.mainArray[this.indexLastValue] = data;
                this.indexLastValue++;
            }else{
                moveToRigth(index);
                this.mainArray[index] = data;
                this.indexLastValue++;
            }
        }
    }

    public void addFirst(T data){
        moveToRigth(0);
        this.indexLastValue++;
        this.mainArray[0]=data;
    }

    @Override
    public T delete(int index) throws ListOutOfIndex, EmptyListException {
        if (isEmpty()){
            throw new EmptyListException("La lista no contiene elementos.");
        }
        if (index >= this.indexLastValue){
            throw new ListOutOfIndex("Maximo lugar " + this.indexLastValue);
        }
        T eliminatedObject = this.mainArray[index];
        moveToLeft(index);
        this.indexLastValue--;
        return eliminatedObject;
    }

    public T deleteLast() throws EmptyListException {
        if (isEmpty()){
            throw new EmptyListException("La lista no contiene elementos.");
        }
        this.indexLastValue--;
        T toReturn = this.mainArray[indexLastValue];
        this.mainArray[indexLastValue] = null;
        return toReturn;
    }

    public void deleteValue(T data) throws EmptyListException, ListOutOfIndex, ValueNoExist{
        for (int indice=0; indice<this.indexLastValue; indice++){
            if(data.equals(this.mainArray[indice])){
                try{
                    delete(indice);
                } catch (ListOutOfIndex e) {
                    throw new ListOutOfIndex("e");
                } catch (EmptyListException e){
                    throw new EmptyListException("e");
                }
            }
            throw new ValueNoExist("El valor no existe.");
        }
    }

    @Override
    public int size() {
        return this.indexLastValue;
    }

    public boolean contains(T data) {
        for (T current : this.mainArray){
            if (data.equals(current)){
                return true;
            } else if(current==null){
                break;
            }
        }
        return false;
    }

    public T get(int index) throws ListOutOfIndex, EmptyListException{
        if (index>=this.indexLastValue){
            throw new ListOutOfIndex("Index no valido");
        }
        if (isEmpty()){
            throw new EmptyListException("Empty array");
        }
        return this.mainArray[index];
    }

    public void intercambiate(int indexOne, int indexTwo) throws EmptyListException, ListOutOfIndex{
        if (indexOne>=this.indexLastValue){
            throw new ListOutOfIndex("Index no valido");
        }

        T secondData;
        try {
            secondData = get(indexTwo);
        } catch (ListOutOfIndex e){
            throw new ListOutOfIndex("Index no valido");
        } catch (EmptyListException e){
            throw new EmptyListException("Empty Array");
        }

        this.mainArray[indexTwo]=this.mainArray[indexOne];
        this.mainArray[indexOne] = secondData;
    }

    private void incrementLenght(){
        T[] newArray = (T[]) new Object[(this.mainArray.length) * 2];
        System.arraycopy(this.mainArray, 0, newArray, 0, this.mainArray.length);
        this.mainArray = newArray;
    }

    private void moveToRigth(int positionIndex){
        for (int indice=indexLastValue; indice > positionIndex; indice--){
            this.mainArray[indice] = this.mainArray[indice-1];
        }
        this.mainArray[positionIndex]=null;
    }

    private void moveToLeft(int initialPosition) {
        for(int indice=initialPosition; indice<this.indexLastValue; indice++){
            this.mainArray[indice]=this.mainArray[indice+1];
        }
    }
}
