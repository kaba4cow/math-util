package com.kaba4cow.mathutil.matrix;

interface Matrix<T extends Matrix<T>> {

	public float determinant();

	public T identity();

	public T negate();

	public T transpose();

	public T multiply(T matrix);

	public T invert();

	public T copy();

	public T set(T matrix);

	public T fromArray(float[] array);

	public float[] toArray();

}
