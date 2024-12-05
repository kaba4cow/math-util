package com.kaba4cow.mathutil.vector;

interface Vector<T extends Vector<T>> {

	public T interpolate(T target, float t);

	public T add(T vector);

	public T sub(T vector);

	public T add(T vector, float scale);

	public T sub(T vector, float scale);

	public float dot(T vector);

	public float lengthSquared();

	default public float length() {
		return (float) Math.sqrt(lengthSquared());
	}

	public float distanceSquared(T vector);

	default public float distance(T vector) {
		return (float) Math.sqrt(distanceSquared(vector));
	}

	public T multiply(float value);

	default public T divide(float value) {
		return multiply(1.0f / value);
	}

	default public T negate() {
		return multiply(-1.0f);
	}

	default public T normalize() {
		return divide(length());
	}

	public T copy();

	public T set(T vector);

	public float[] toArray();

}
