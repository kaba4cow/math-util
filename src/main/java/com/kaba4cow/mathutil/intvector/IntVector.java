package com.kaba4cow.mathutil.intvector;

interface IntVector<T extends IntVector<T>> {

	public T add(T vector);

	public T sub(T vector);

	public T add(T vector, int scale);

	public T sub(T vector, int scale);

	public int dot(T vector);

	public int length();

	public int distance(T vector);

	public T multiply(int value);

	public T divide(int value);

	default public T negate() {
		return multiply(-1);
	}

	public T copy();

	public T set(T vector);

	public int[] toArray();

}
