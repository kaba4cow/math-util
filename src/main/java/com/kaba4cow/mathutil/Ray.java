package com.kaba4cow.mathutil;

import java.util.Objects;

import com.kaba4cow.mathutil.vector.Vector3;

public class Ray {

	private final Vector3 position;
	private final Vector3 direction;

	public Ray() {
		this.position = new Vector3();
		this.direction = new Vector3();
	}

	public Vector3 intersection(Ray ray) {
		float cross = this.direction.copy().cross(ray.direction).lengthSquared();
		float t = ray.position.copy().sub(this.position).cross(ray.direction).dot(this.direction) / cross;
		return this.position.copy().add(this.direction, t);
	}

	public Ray lookAt(Vector3 target) {
		direction.set(target.copy().sub(position)).normalize();
		return this;
	}

	public Ray normalize() {
		direction.normalize();
		return this;
	}

	public boolean parallel(Ray ray) {
		return Math.abs(direction.dot(ray.direction)) >= 1.0;
	}

	public Vector3 position() {
		return position;
	}

	public Ray position(Vector3 position) {
		this.position.set(position);
		return this;
	}

	public Vector3 direction() {
		return direction;
	}

	public Ray direction(Vector3 direction) {
		this.direction.set(direction);
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(direction, position);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ray other = (Ray) obj;
		return Objects.equals(direction, other.direction) && Objects.equals(position, other.position);
	}

	@Override
	public String toString() {
		return String.format("Rayf [%s, %s]", position, direction);
	}

}
