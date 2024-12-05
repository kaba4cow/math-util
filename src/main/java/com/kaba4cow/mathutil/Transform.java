package com.kaba4cow.mathutil;

import java.util.Objects;

import com.kaba4cow.mathutil.matrix.Matrix4;
import com.kaba4cow.mathutil.vector.Vector3;

public class Transform {

	private final Vector3 position;
	private final Quaternion rotation;

	public Transform() {
		this.position = new Vector3();
		this.rotation = new Quaternion();
	}

	public Transform interpolate(Transform target, float t) {
		position.interpolate(target.position, t);
		rotation.interpolate(target.rotation, t);
		return this;
	}

	public Transform fromMatrix(Matrix4 matrix) {
		position.set(matrix.m30, matrix.m31, matrix.m32);
		rotation.fromMatrix(matrix);
		return this;
	}

	public Matrix4 asMatrix() {
		return new Matrix4().identity().translate(position).multiply(rotation.asMatrix());
	}

	public Vector3 position() {
		return position;
	}

	public Quaternion rotation() {
		return rotation;
	}

	public Transform set(Transform transform) {
		position.set(transform.position);
		rotation.set(transform.rotation);
		return this;
	}

	public Transform copy() {
		return new Transform().set(this);
	}

	@Override
	public int hashCode() {
		return Objects.hash(position, rotation);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transform other = (Transform) obj;
		return Objects.equals(position, other.position) && Objects.equals(rotation, other.rotation);
	}

	@Override
	public String toString() {
		return String.format("Transformf [%s, %s]", position, rotation);
	}

}
