package com.kaba4cow.mathutil;

import java.util.Objects;

import com.kaba4cow.mathutil.matrix.Matrix4;
import com.kaba4cow.mathutil.vector.Vector3;
import com.kaba4cow.mathutil.vector.Vector4;

public class Quaternion {

	private float x;
	private float y;
	private float z;
	private float w;

	public Quaternion() {
		this.x = 0.0f;
		this.y = 0.0f;
		this.z = 0.0f;
		this.w = 1.0f;
	}

	public Quaternion interpolate(Quaternion target, float t) {
		return this.w * target.w + this.x * target.x + this.y * target.y + this.z * target.z < 0.0f//
				? set(//
						this.x - t * (this.x + target.x), //
						this.y - t * (this.y + target.y), //
						this.z - t * (this.z + target.z), //
						this.w - t * (this.w + target.w))
				: set(//
						this.x - t * (this.x - target.x), //
						this.y - t * (this.y - target.y), //
						this.z - t * (this.z - target.z), //
						this.w - t * (this.w - target.w));
	}

	public Quaternion set(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		return normalize();
	}

	public Quaternion set(Quaternion quaternion) {
		return set(quaternion.x, quaternion.y, quaternion.z, quaternion.w);
	}

	public Quaternion set(Vector4 vector) {
		return set(vector.x, vector.y, vector.z, vector.w);
	}

	public Quaternion fromMatrix(Matrix4 matrix) {
		float diagonal = matrix.m00 + matrix.m11 + matrix.m22;
		if (diagonal > 0.0f) {
			float w4 = (float) Math.sqrt(1.0 + diagonal) * 2.0f;
			return set(//
					(matrix.m21 - matrix.m12) / w4, //
					(matrix.m02 - matrix.m20) / w4, //
					(matrix.m10 - matrix.m01) / w4, //
					0.25f * w4);
		} else if (matrix.m00 > matrix.m11 && matrix.m00 > matrix.m22) {
			float x4 = (float) Math.sqrt(1.0 + matrix.m00 - matrix.m11 - matrix.m22) * 2.0f;
			return set(//
					0.25f * x4, //
					(matrix.m01 + matrix.m10) / x4, //
					(matrix.m02 + matrix.m20) / x4, //
					(matrix.m21 - matrix.m12) / x4);
		} else if (matrix.m11 > matrix.m22) {
			float y4 = (float) Math.sqrt(1.0 + matrix.m11 - matrix.m00 - matrix.m22) * 2.0f;
			return set(//
					(matrix.m01 + matrix.m10) / y4, //
					0.25f * y4, //
					(matrix.m12 + matrix.m21) / y4, //
					(matrix.m02 - matrix.m20) / y4);
		} else {
			float z4 = (float) Math.sqrt(1.0 + matrix.m22 - matrix.m00 - matrix.m11) * 2.0f;
			return set(//
					(matrix.m02 + matrix.m20) / z4, //
					(matrix.m12 + matrix.m21) / z4, //
					0.25f * z4, //
					(matrix.m10 - matrix.m01) / z4);
		}
	}

	public Quaternion fromDirection(Vector3 direction, Vector3 up) {
		Vector3 right = up.copy().cross(direction).normalize();
		Vector3 newUp = direction.copy().cross(right).normalize();
		return fromMatrix(new Matrix4()//
				.row0(right.x, newUp.x, direction.x, 0.0f)//
				.row1(right.y, newUp.y, direction.y, 0.0f)//
				.row2(right.z, newUp.z, direction.z, 0.0f)//
				.row3(0.0f, 0.0f, 0.0f, 1.0f));
	}

	public Quaternion rotate(Vector3 axis, float angle) {
		float sin = (float) Math.sin(0.5 * angle);
		float cos = (float) Math.cos(0.5 * angle);
		return multiply(new Quaternion().set(//
				axis.x * sin, //
				axis.y * sin, //
				axis.z * sin, //
				cos));
	}

	public Vector3 asDirection() {
		return new Vector3().set(//
				2.0f * (x * z + w * y), //
				2.0f * (y * z - w * x), //
				1.0f - 2.0f * (x * x + y * y))//
				.normalize();
	}

	public Matrix4 asMatrix() {
		return new Matrix4()//
				.row0(1.0f - 2.0f * (y * y + z * z), 2.0f * (x * y - z * w), 2.0f * (x * z + y * w), 0.0f)//
				.row1(2.0f * (x * y + z * w), 1.0f - 2.0f * (x * x + z * z), 2.0f * (y * z - w * w), 0.0f)//
				.row2(2.0f * (x * z - y * w), 2.0f * (y * z + w * w), 1.0f - 2.0f * (x * x + y * y), 0.0f)//
				.row3(0.0f, 0.0f, 0.0f, 1.0f);
	}

	public Vector4 asVector() {
		return new Vector4().set(x, y, z, w);
	}

	public float[] toArray() {
		return new float[] { x, y, z, w };
	}

	public Quaternion normalize() {
		float inverse = 1.0f / (float) Math.sqrt(x * x + y * y + z * z + w * w);
		this.x *= inverse;
		this.y *= inverse;
		this.z *= inverse;
		this.w *= inverse;
		return this;
	}

	public Quaternion multiply(Quaternion quaternion) {
		return set(//
				this.w * quaternion.x + this.x * quaternion.w + this.y * quaternion.z - this.z * quaternion.y, //
				this.w * quaternion.y - this.x * quaternion.z + this.y * quaternion.w + this.z * quaternion.x, //
				this.w * quaternion.z + this.x * quaternion.y - this.y * quaternion.x + this.z * quaternion.w, //
				this.w * quaternion.w - this.x * quaternion.x - this.y * quaternion.y - this.z * quaternion.z);
	}

	public Quaternion conjugate() {
		return set(-x, -y, -z, w);
	}

	public Quaternion copy() {
		return new Quaternion().set(this);
	}

	@Override
	public int hashCode() {
		return Objects.hash(w, x, y, z);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quaternion other = (Quaternion) obj;
		return Float.floatToIntBits(w) == Float.floatToIntBits(other.w)
				&& Float.floatToIntBits(x) == Float.floatToIntBits(other.x)
				&& Float.floatToIntBits(y) == Float.floatToIntBits(other.y)
				&& Float.floatToIntBits(z) == Float.floatToIntBits(other.z);
	}

	@Override
	public String toString() {
		return String.format("Quaternionf [%s, %s, %s, %s]", x, y, z, w);
	}

}
