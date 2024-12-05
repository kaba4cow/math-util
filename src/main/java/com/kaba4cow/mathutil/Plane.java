package com.kaba4cow.mathutil;

import java.util.Objects;

import com.kaba4cow.mathutil.vector.Vector3;

public class Plane {

	private final Vector3 normal;
	private float distance;

	public Plane() {
		this.normal = new Vector3().set(0.0f, 1.0f, 0.0f);
		this.distance = 0.0f;
	}

	public Vector3 tangent() {
		Vector3 arbitrary = Math.abs(normal.x) < 0.9//
				? new Vector3().set(1.0f, 0.0f, 0.0f)//
				: new Vector3().set(0.0f, 1.0f, 0.0f);
		return normal.copy().cross(arbitrary).normalize();
	}

	public Vector3 binormal() {
		return normal.copy().cross(tangent()).normalize();
	}

	public float distance(Vector3 point) {
		return normal.dot(point) + distance;
	}

	public Plane fromPoints(Vector3 a, Vector3 b, Vector3 c) {
		return normal(MathUtil.calculateNormal(a, b, c)).distance(normal.dot(a));
	}

	public boolean parallel(Plane plane) {
		return this.normal.copy().cross(plane.normal).lengthSquared() < 1.0e-6f;
	}

	public boolean intersects(Plane plane) {
		return !parallel(plane);
	}

	public Ray intersection(Plane plane) {
		if (parallel(plane))
			return null;
		Vector3 direction = this.normal.copy().cross(plane.normal).normalize();
		float determinant = this.normal.dot(plane.normal.copy().cross(direction));
		Vector3 position = plane.normal.cross(direction).multiply(this.distance)
				.add(this.normal.copy().cross(direction).multiply(plane.distance)).divide(determinant);
		return new Ray().position(position).direction(direction);
	}

	public Vector3 intersection(Plane plane1, Plane plane2) {
		Vector3 point = plane1.normal.copy().cross(plane2.normal).multiply(-this.distance)//
				.sub(plane2.normal.copy().cross(this.normal), plane1.distance)//
				.sub(this.normal.copy().cross(plane1.normal), plane2.distance);
		return point.divide(this.normal.dot(plane1.normal.copy().cross(plane2.normal)));
	}

	public boolean intersects(Plane plane1, Plane plane2) {
		return Math.abs(this.normal.dot(plane1.normal.copy().cross(plane2.normal))) >= 1.0e-6;
	}

	public Vector3 normal() {
		return normal;
	}

	public Plane normal(Vector3 normal) {
		this.normal.set(normal).normalize();
		return this;
	}

	public float distance() {
		return distance;
	}

	public Plane distance(float distance) {
		this.distance = distance;
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(distance, normal);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plane other = (Plane) obj;
		return Float.floatToIntBits(distance) == Float.floatToIntBits(other.distance) && Objects.equals(normal, other.normal);
	}

	@Override
	public String toString() {
		return String.format("Planef [%s, %s]", normal, distance);
	}

}
