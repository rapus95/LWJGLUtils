package xor.vecmat;

import xor.vecmat.mat.Mat_base;
import xor.vecmat.mat.d.DMat;
import xor.vecmat.mat.f.Mat;
import xor.vecmat.mat.f.Mat1;
import xor.vecmat.mat.f.Mat2;
import xor.vecmat.mat.f.Mat3;
import xor.vecmat.mat.f.Mat4;
import xor.vecmat.mat.f.MatN;
import xor.vecmat.quat.Quat_base;
import xor.vecmat.vec.Vec_base;
import xor.vecmat.vec.Vec_cross;
import xor.vecmat.vec.Vec_float;
import xor.vecmat.vec.Vec_int;
import xor.vecmat.vec.Vec_numeric;
import xor.vecmat.vec.b.BVec;
import xor.vecmat.vec.d.DVec;
import xor.vecmat.vec.f.Vec;
import xor.vecmat.vec.f.Vec1;
import xor.vecmat.vec.f.Vec2;
import xor.vecmat.vec.f.Vec3;
import xor.vecmat.vec.f.Vec4;
import xor.vecmat.vec.f.VecN;
import xor.vecmat.vec.i.IVec;
import xor.vecmat.vec.l.LVec;

public final class VecMath {

	public static final double LOG2 = log(2);
	private static final float L2F = (float) LOG2;
	public static final double PI = java.lang.Math.PI;
	public static final double E = java.lang.Math.E;

	private VecMath() {
	}

	public static final <M extends Mat_base<M, ?, ?, N>, N extends Number> M add(M v1, N v2) {
		return v1.add(v2);
	}

	public static final <M extends Mat_base<M, ?, ?, N>, N extends Number> M add(N v1, M v2) {
		return v2.add(v1);
	}

	public static final <M extends Mat_base<M, ?, ?, ?>> M add(M v1, M v2) {
		return v1.add(v2);
	}

	public static final <M extends Mat<M, ?, ?>> M add(M v1, float v2) {
		return v1.add(v2);
	}

	public static final <M extends Mat<M, ?, ?>> M add(float v1, M v2) {
		return v2.add(v1);
	}

	public static final <M extends DMat<M, ?, ?>> M add(M v1, double v2) {
		return v1.add(v2);
	}

	public static final <M extends DMat<M, ?, ?>> M add(double v1, M v2) {
		return v2.add(v1);
	}

	public static final <V extends Vec_numeric<V, ?, N>, N extends Number> V add(V v1, N v2) {
		return v1.add(v2);
	}

	public static final <V extends Vec_numeric<V, ?, N>, N extends Number> V add(N v1, V v2) {
		return v2.add(v1);
	}

	public static final <V extends Vec_numeric<V, ?, ?>> V add(V v1, V v2) {
		return v1.add(v2);
	}

	public static final <Q extends Quat_base<Q, ?>> Q add(Q v1, Q v2) {
		return v1.add(v2);
	}

	public static final int add(int v1, int v2) {
		return v1 + v2;
	}

	public static final <V extends IVec<V, ?>> V add(V v1, int v2) {
		return v1.add(v2);
	}

	public static final <V extends IVec<V, ?>> V add(int v1, V v2) {
		return v2.add(v1);
	}

	public static final long add(long v1, long v2) {
		return v1 + v2;
	}

	public static final <V extends LVec<V, ?>> V add(V v1, long v2) {
		return v1.add(v2);
	}

	public static final <V extends LVec<V, ?>> V add(long v1, V v2) {
		return v2.add(v1);
	}

	public static final float add(float v1, float v2) {
		return v1 + v2;
	}

	public static final <V extends Vec<V, ?>> V add(V v1, float v2) {
		return v1.add(v2);
	}

	public static final <V extends Vec<V, ?>> V add(float v1, V v2) {
		return v2.add(v1);
	}

	public static final double add(double v1, double v2) {
		return v1 + v2;
	}

	public static final <V extends DVec<V, ?>> V add(V v1, double v2) {
		return v1.add(v2);
	}

	public static final <V extends DVec<V, ?>> V add(double v1, V v2) {
		return v2.add(v1);
	}

	public static final <V extends Vec_float<V, ?, N>, N extends Number> V add_scaled(V v1, V v2, N v3) {
		return v1.add_scaled(v2, v3);
	}

	public static final <V extends Vec_float<V, ?, ?>> V add_scaled(V v1, V v2, V v3) {
		return v1.add_scaled(v2, v3);
	}

	public static final float add_scaled(float v1, float v2, float v3) {
		return v1 + v2 * v3;
	}

	public static final <V extends Vec<V, ?>> V add_scaled(V v1, V v2, float v3) {
		return v1.add_scaled(v2, v3);
	}

	public static final double add_scaled(double v1, double v2, double v3) {
		return v1 + v2 * v3;
	}

	public static final <V extends DVec<V, ?>> V add_scaled(V v1, V v2, double v3) {
		return v1.add_scaled(v2, v3);
	}

	public static final <M extends Mat_base<M, ?, ?, N>, N extends Number> M sub(M v1, N v2) {
		return v1.sub(v2);
	}

	public static final <M extends Mat_base<M, ?, ?, N>, N extends Number> M sub(N v1, M v2) {
		return v2.sub2(v1);
	}

	public static final <M extends Mat_base<M, ?, ?, ?>> M sub(M v1, M v2) {
		return v1.sub(v2);
	}

	public static final <M extends Mat<M, ?, ?>> M sub(M v1, float v2) {
		return v1.sub(v2);
	}

	public static final <M extends Mat<M, ?, ?>> M sub(float v1, M v2) {
		return v2.sub2(v1);
	}

	public static final <M extends DMat<M, ?, ?>> M sub(M v1, double v2) {
		return v1.sub(v2);
	}

	public static final <M extends DMat<M, ?, ?>> M sub(double v1, M v2) {
		return v2.sub2(v1);
	}

	public static final <V extends Vec_numeric<V, ?, N>, N extends Number> V sub(V v1, N v2) {
		return v1.sub(v2);
	}

	public static final <V extends Vec_numeric<V, ?, N>, N extends Number> V sub(N v1, V v2) {
		return v2.sub2(v1);
	}

	public static final <V extends Vec_numeric<V, ?, ?>> V sub(V v1, V v2) {
		return v1.sub(v2);
	}

	public static final <Q extends Quat_base<Q, ?>> Q sub(Q v1, Q v2) {
		return v1.sub(v2);
	}

	public static final int sub(int v1, int v2) {
		return v1 - v2;
	}

	public static final <V extends IVec<V, ?>> V sub(V v1, int v2) {
		return v1.sub(v2);
	}

	public static final <V extends IVec<V, ?>> V sub(int v1, V v2) {
		return v2.sub2(v1);
	}

	public static final long sub(long v1, long v2) {
		return v1 - v2;
	}

	public static final <V extends LVec<V, ?>> V sub(V v1, long v2) {
		return v1.sub(v2);
	}

	public static final <V extends LVec<V, ?>> V sub(long v1, V v2) {
		return v2.sub2(v1);
	}

	public static final float sub(float v1, float v2) {
		return v1 - v2;
	}

	public static final <V extends Vec<V, ?>> V sub(V v1, float v2) {
		return v1.sub(v2);
	}

	public static final <V extends Vec<V, ?>> V sub(float v1, V v2) {
		return v2.sub2(v1);
	}

	public static final double sub(double v1, double v2) {
		return v1 - v2;
	}

	public static final <V extends DVec<V, ?>> V sub(V v1, double v2) {
		return v1.sub(v2);
	}

	public static final <V extends DVec<V, ?>> V sub(double v1, V v2) {
		return v2.sub2(v1);
	}

	public static final <M extends Mat_base<M, ?, ?, ?>> M neg(M v1) {
		return v1.neg();
	}

	public static final <V extends Vec_numeric<V, ?, ?>> V neg(V v1) {
		return v1.neg();
	}

	public static final <Q extends Quat_base<Q, ?>> Q neg(Q v1) {
		return v1.neg();
	}

	public static final int neg(int v1) {
		return -v1;
	}

	public static final long neg(long v1) {
		return -v1;
	}

	public static final float neg(float v1) {
		return -v1;
	}

	public static final double neg(double v1) {
		return -v1;
	}

	public static final <M extends Mat_base<M, ?, ?, N>, N extends Number> M mul(M v1, N v2) {
		return v1.mul(v2);
	}

	public static final <M extends Mat_base<M, ?, ?, ?>> M compMul(M v1, M v2) {
		return v1.compMul(v2);
	}

	public static final <M extends Mat<M, ?, ?>> M mul(M v1, float v2) {
		return v1.mul(v2);
	}

	public static final <M extends DMat<M, ?, ?>> M mul(M v1, double v2) {
		return v1.mul(v2);
	}

	public static final <M extends Mat_base<M, M2, ?, N>, M2 extends Mat_base<M2, ?, ?, N>, N extends Number> M2 mul(M v1, M v2) {
		return v1.mul(v2);
	}

	public static final <M extends Mat_base<M, ?, ?, N>, N extends Number> M mulU(Mat_base<?, ?, ?, N> v1, Mat_base<?, ?, ?, N> v2) {
		return v1.mulU(v2);
	}

	public static final <M extends Mat_base<M, ?, V, N>, V extends Vec_numeric<V, ?, N>, N extends Number> V mul(M v1, V v2) {
		return v1.mul(v2);
	}

	public static final <M extends Mat_base<M, ?, V, N>, V extends Vec_numeric<V, ?, N>, R extends V, N extends Number> R mulV(M v1, V v2) {
		return v1.mulV(v2);
	}

	public static final <V extends Vec_numeric<V, ?, N>, N extends Number> V mul(V v1, N v2) {
		return v1.mul(v2);
	}

	public static final <V extends Vec_numeric<V, ?, N>, N extends Number> V mul(N v1, V v2) {
		return v2.mul(v1);
	}

	public static final <V extends Vec_numeric<V, ?, ?>> V mul(V v1, V v2) {
		return v1.mul(v2);
	}

	public static final <Q extends Quat_base<Q, ?>> Q mul(Q v1, Q v2) {
		return v1.mul(v2);
	}

	public static final int mul(int v1, int v2) {
		return v1 * v2;
	}

	public static final <V extends IVec<V, ?>> V mul(V v1, int v2) {
		return v1.mul(v2);
	}

	public static final <V extends IVec<V, ?>> V mul(int v1, V v2) {
		return v2.mul(v1);
	}

	public static final long mul(long v1, long v2) {
		return v1 * v2;
	}

	public static final <V extends LVec<V, ?>> V mul(V v1, long v2) {
		return v1.mul(v2);
	}

	public static final <V extends LVec<V, ?>> V mul(long v1, V v2) {
		return v2.mul(v1);
	}

	public static final float mul(float v1, float v2) {
		return v1 * v2;
	}

	public static final <V extends Vec<V, ?>> V mul(V v1, float v2) {
		return v1.mul(v2);
	}

	public static final <V extends Vec<V, ?>> V mul(float v1, V v2) {
		return v2.mul(v1);
	}

	public static final double mul(double v1, double v2) {
		return v1 * v2;
	}

	public static final <V extends DVec<V, ?>> V mul(V v1, double v2) {
		return v1.mul(v2);
	}

	public static final <V extends DVec<V, ?>> V mul(double v1, V v2) {
		return v2.mul(v1);
	}

	public static final <M extends Mat_base<M, ?, ?, N>, N extends Number> M compDiv(M v1, N v2) {
		return v1.compDiv(v2);
	}

	public static final <M extends Mat_base<M, ?, ?, ?>> M compDiv(M v1, M v2) {
		return v1.compDiv(v2);
	}

	public static final <M extends Mat<M, ?, ?>> M compDiv(M v1, float v2) {
		return v1.compDiv(v2);
	}

	public static final <M extends DMat<M, ?, ?>> M compDiv(M v1, double v2) {
		return v1.compDiv(v2);
	}

	public static final float divf(int v1, int v2) {
		return (float) v1 / (float) v2;
	}

	public static final float divf(long v1, long v2) {
		return (float) v1 / (float) v2;
	}

	public static final double divd(int v1, int v2) {
		return (double) v1 / (double) v2;
	}

	public static final double divd(long v1, long v2) {
		return (double) v1 / (double) v2;
	}

	public static final double divd(float v1, float v2) {
		return (double) v1 / (double) v2;
	}

	public static final <V extends Vec_numeric<V, ?, N>, N extends Number> V div(V v1, N v2) {
		return v1.div(v2);
	}

	public static final <V extends Vec_numeric<V, ?, N>, N extends Number> V div(N v1, V v2) {
		return v2.div2(v1);
	}

	public static final <V extends Vec_numeric<V, ?, ?>> V div(V v1, V v2) {
		return v1.div(v2);
	}

	public static final <Q extends Quat_base<Q, ?>> Q div(Q v1, Q v2) {
		return v1.mul(v2.inverse());
	}

	public static final int idiv(int v1, int v2) {
		return v1 / v2;
	}

	public static final <V extends IVec<V, ?>> V div(V v1, int v2) {
		return v1.div(v2);
	}

	public static final <V extends IVec<V, ?>> V div(int v1, V v2) {
		return v2.div2(v1);
	}

	public static final long idiv(long v1, long v2) {
		return v1 / v2;
	}

	public static final <V extends LVec<V, ?>> V div(V v1, long v2) {
		return v1.div(v2);
	}

	public static final <V extends LVec<V, ?>> V div(long v1, V v2) {
		return v2.div2(v1);
	}

	public static final float div(float v1, float v2) {
		return v1 / v2;
	}

	public static final <V extends Vec<V, ?>> V div(V v1, float v2) {
		return v1.div(v2);
	}

	public static final <V extends Vec<V, ?>> V div(float v1, V v2) {
		return v2.div2(v1);
	}

	public static final double div(double v1, double v2) {
		return v1 / v2;
	}

	public static final <V extends DVec<V, ?>> V div(V v1, double v2) {
		return v1.div(v2);
	}

	public static final <V extends DVec<V, ?>> V div(double v1, V v2) {
		return v2.div2(v1);
	}

	public static final <M extends Mat_base<M, ?, ?, N>, N extends Number> M compMod(M v1, N v2) {
		return v1.compMod(v2);
	}

	public static final <M extends Mat_base<M, ?, ?, ?>> M compMod(M v1, M v2) {
		return v1.compMod(v2);
	}

	public static final <M extends Mat<M, ?, ?>> M compMod(M v1, float v2) {
		return v1.compMod(v2);
	}

	public static final <M extends DMat<M, ?, ?>> M compMod(M v1, double v2) {
		return v1.compMod(v2);
	}

	public static final <V extends Vec_numeric<V, ?, N>, N extends Number> V mod(V v1, N v2) {
		return v1.mod(v2);
	}

	public static final <V extends Vec_numeric<V, ?, N>, N extends Number> V mod(N v1, V v2) {
		return v2.mod2(v1);
	}

	public static final <V extends Vec_numeric<V, ?, ?>> V mod(V v1, V v2) {
		return v1.mod(v2);
	}

	public static final int mod(int v1, int v2) {
		return v1 % v2;
	}

	public static final <V extends IVec<V, ?>> V mod(V v1, int v2) {
		return v1.mod(v2);
	}

	public static final <V extends IVec<V, ?>> V mod(int v1, V v2) {
		return v2.mod2(v1);
	}

	public static final long mod(long v1, long v2) {
		return v1 % v2;
	}

	public static final <V extends LVec<V, ?>> V mod(V v1, long v2) {
		return v1.mod(v2);
	}

	public static final <V extends LVec<V, ?>> V mod(long v1, V v2) {
		return v2.mod2(v1);
	}

	public static final float mod(float v1, float v2) {
		return v1 % v2;
	}

	public static final <V extends Vec<V, ?>> V mod(V v1, float v2) {
		return v1.mod(v2);
	}

	public static final <V extends Vec<V, ?>> V mod(float v1, V v2) {
		return v2.mod2(v1);
	}

	public static final double mod(double v1, double v2) {
		return v1 % v2;
	}

	public static final <V extends DVec<V, ?>> V mod(V v1, double v2) {
		return v1.mod(v2);
	}

	public static final <V extends DVec<V, ?>> V mod(double v1, V v2) {
		return v2.mod2(v1);
	}

	public static final <M extends Mat_base<M, M, ?, ?>> M pow(M v1, int v2) {
		return v1.pow(v2);
	}

	public static final <V extends Vec_numeric<V, ?, N>, N extends Number> V pow(V v1, N v2) {
		return v1.pow(v2);
	}

	public static final <V extends Vec_numeric<V, ?, N>, N extends Number> V pow(N v1, V v2) {
		return v2.pow2(v1);
	}

	public static final <V extends Vec_numeric<V, ?, ?>> V pow(V v1, V v2) {
		return v1.pow(v2);
	}

	public static final int pow(int v1, int v2) {
		return (int) java.lang.Math.pow(v1, v2);
	}

	public static final <V extends IVec<V, ?>> V pow(V v1, int v2) {
		return v1.pow(v2);
	}

	public static final <V extends IVec<V, ?>> V pow(int v1, V v2) {
		return v2.pow2(v1);
	}

	public static final long pow(long v1, long v2) {
		return (long) java.lang.Math.pow(v1, v2);
	}

	public static final <V extends LVec<V, ?>> V pow(V v1, long v2) {
		return v1.pow(v2);
	}

	public static final <V extends LVec<V, ?>> V pow(long v1, V v2) {
		return v2.pow2(v1);
	}

	public static final float pow(float v1, float v2) {
		return (float) java.lang.Math.pow(v1, v2);
	}

	public static final <V extends Vec<V, ?>> V pow(V v1, float v2) {
		return v1.pow(v2);
	}

	public static final <V extends Vec<V, ?>> V pow(float v1, V v2) {
		return v2.pow2(v1);
	}

	public static final double pow(double v1, double v2) {
		return java.lang.Math.pow(v1, v2);
	}

	public static final <V extends DVec<V, ?>> V pow(V v1, double v2) {
		return v1.pow(v2);
	}

	public static final <V extends DVec<V, ?>> V pow(double v1, V v2) {
		return v2.pow2(v1);
	}

	public static final <V extends Vec_float<V, ?, ?>> V radians(V v1) {
		return v1.radians();
	}

	public static final float radians(float v1) {
		return (float) java.lang.Math.toRadians(v1);
	}

	public static final double radians(double v1) {
		return java.lang.Math.toRadians(v1);
	}

	public static final <V extends Vec_float<V, ?, ?>> V degrees(V v1) {
		return v1.degrees();
	}

	public static final float degrees(float v1) {
		return (float) java.lang.Math.toDegrees(v1);
	}

	public static final double degrees(double v1) {
		return java.lang.Math.toDegrees(v1);
	}

	public static final <V extends Vec_float<V, ?, ?>> V sin(V v1) {
		return v1.sin();
	}

	public static final float sin(float v1) {
		return (float) java.lang.Math.sin(v1);
	}

	public static final double sin(double v1) {
		return java.lang.Math.sin(v1);
	}

	public static final <V extends Vec_float<V, ?, ?>> V cos(V v1) {
		return v1.cos();
	}

	public static final float cos(float v1) {
		return (float) java.lang.Math.cos(v1);
	}

	public static final double cos(double v1) {
		return java.lang.Math.cos(v1);
	}

	public static final <V extends Vec_float<V, ?, ?>> V tan(V v1) {
		return v1.tan();
	}

	public static final float tan(float v1) {
		return (float) java.lang.Math.tan(v1);
	}

	public static final double tan(double v1) {
		return java.lang.Math.tan(v1);
	}

	public static final <V extends Vec_float<V, ?, ?>> V asin(V v1) {
		return v1.asin();
	}

	public static final float asin(float v1) {
		return (float) java.lang.Math.asin(v1);
	}

	public static final double asin(double v1) {
		return java.lang.Math.asin(v1);
	}

	public static final <V extends Vec_float<V, ?, ?>> V acos(V v1) {
		return v1.acos();
	}

	public static final float acos(float v1) {
		return (float) java.lang.Math.acos(v1);
	}

	public static final double acos(double v1) {
		return java.lang.Math.acos(v1);
	}

	public static final <V extends Vec_float<V, ?, ?>> V atan(V v1) {
		return v1.atan();
	}

	public static final float atan(float v1) {
		return (float) java.lang.Math.atan(v1);
	}

	public static final double atan(double v1) {
		return java.lang.Math.atan(v1);
	}

	public static final float atan2(float v1, float v2) {
		return (float) java.lang.Math.atan2(v1, v2);
	}

	public static final double atan2(double v1, double v2) {
		return java.lang.Math.atan2(v1, v2);
	}

	public static final <V extends Vec_float<V, ?, ?>> V sinh(V v1) {
		return v1.sinh();
	}

	public static final float sinh(float v1) {
		return (exp(v1) - exp(-v1)) * 0.5f;
	}

	public static final double sinh(double v1) {
		return (exp(v1) - exp(-v1)) * 0.5;
	}

	public static final <V extends Vec_float<V, ?, ?>> V cosh(V v1) {
		return v1.cosh();
	}

	public static final float cosh(float v1) {
		return (exp(v1) + exp(-v1)) * 0.5f;
	}

	public static final double cosh(double v1) {
		return (exp(v1) + exp(-v1)) * 0.5;
	}

	public static final <V extends Vec_float<V, ?, ?>> V tanh(V v1) {
		return v1.tanh();
	}

	public static final float tanh(float v1) {
		return (exp(v1) - exp(-v1)) / (exp(v1) + exp(-v1));
	}

	public static final double tanh(double v1) {
		return (exp(v1) - exp(-v1)) / (exp(v1) + exp(-v1));
	}

	public static final <V extends Vec_float<V, ?, ?>> V asinh(V v1) {
		return v1.asinh();
	}

	public static final float asinh(float v1) {
		return log(v1 + sqrt(v1 * v1 + 1.0f));
	}

	public static final double asinh(double v1) {
		return log(v1 + sqrt(v1 * v1 + 1.0));
	}

	public static final <V extends Vec_float<V, ?, ?>> V acosh(V v1) {
		return v1.acosh();
	}

	public static final float acosh(float v1) {
		return log(v1 + sqrt(v1 * v1 - 1.0f));
	}

	public static final double acosh(double v1) {
		return log(v1 + sqrt(v1 * v1 - 1.0));
	}

	public static final <V extends Vec_float<V, ?, ?>> V atanh(V v1) {
		return v1.atanh();
	}

	public static final float atanh(float v1) {
		return log((1.0f + v1) / (1.0f - v1)) * 0.5f;
	}

	public static final double atanh(double v1) {
		return log((1.0 + v1) / (1.0 - v1)) * 0.5;
	}

	public static final <V extends Vec_float<V, ?, ?>> V exp(V v1) {
		return v1.exp();
	}

	public static final float exp(float v1) {
		return (float) java.lang.Math.exp(v1);
	}

	public static final double exp(double v1) {
		return java.lang.Math.exp(v1);
	}

	public static final <V extends Vec_float<V, ?, ?>> V log(V v1) {
		return v1.log();
	}

	public static final float log(float v1) {
		return (float) java.lang.Math.log(v1);
	}

	public static final double log(double v1) {
		return java.lang.Math.log(v1);
	}

	public static final <V extends Vec_float<V, ?, ?>> V exp2(V v1) {
		return v1.exp2();
	}

	public static final float exp2(float v1) {
		return exp(v1 * L2F);
	}

	public static final double exp2(double v1) {
		return exp(v1 * LOG2);
	}

	public static final <V extends Vec_float<V, ?, ?>> V log2(V v1) {
		return v1.log2();
	}

	public static final float log2(float v1) {
		return log(v1) / L2F;
	}

	public static final double log2(double v1) {
		return log(v1) / LOG2;
	}

	public static final <V extends Vec_float<V, ?, ?>> V sqrt(V v1) {
		return v1.sqrt();
	}

	public static final float sqrt(float v1) {
		return (float) java.lang.Math.sqrt(v1);
	}

	public static final double sqrt(double v1) {
		return java.lang.Math.sqrt(v1);
	}

	public static final <V extends Vec_float<V, ?, ?>> V inversesqrt(V v1) {
		return v1.inversesqrt();
	}

	public static final float inversesqrt(float v1) {
		return 1 / sqrt(v1);
	}

	public static final double inversesqrt(double v1) {
		return 1 / sqrt(v1);
	}

	public static final <V extends Vec_numeric<V, ?, ?>> V abs(V v1) {
		return v1.abs();
	}

	public static final int abs(int v1) {
		return v1 < 0 ? -v1 : v1;
	}

	public static final long abs(long v1) {
		return v1 < 0 ? -v1 : v1;
	}

	public static final float abs(float v1) {
		return v1 < 0 ? -v1 : v1;
	}

	public static final double abs(double v1) {
		return v1 < 0 ? -v1 : v1;
	}

	public static final <V extends Vec_numeric<V, ?, ?>> V sign(V v1) {
		return v1.sign();
	}

	public static final int sign(int v1) {
		return v1 > 0 ? 1 : v1 < 0 ? -1 : 0;
	}

	public static final long sign(long v1) {
		return v1 > 0 ? 1 : v1 < 0 ? -1 : 0;
	}

	public static final float sign(float v1) {
		return v1 > 0 ? 1 : v1 < 0 ? -1 : 0;
	}

	public static final double sign(double v1) {
		return v1 > 0 ? 1 : v1 < 0 ? -1 : 0;
	}

	public static final <V extends Vec_float<V, ?, ?>> V roundeven(V v1) {
		return v1.roundeven();
	}

	public static final float roundeven(float v1) {
		return (float) java.lang.Math.rint(v1);
	}

	public static final double roundeven(double v1) {
		return java.lang.Math.rint(v1);
	}

	public static final <V extends Vec_float<V, ?, ?>> V round(V v1) {
		return v1.round();
	}

	public static final float round(float v1) {
		return java.lang.Math.round(v1);
	}

	public static final double round(double v1) {
		return java.lang.Math.round(v1);
	}

	public static final <V extends Vec_float<V, ?, ?>> V trunc(V v1) {
		return v1.trunc();
	}

	public static final float trunc(float v1) {
		return (long) v1;
	}

	public static final double trunc(double v1) {
		return (long) v1;
	}

	public static final <V extends Vec_float<V, ?, ?>> V floor(V v1) {
		return v1.floor();
	}

	public static final float floor(float v1) {
		return (float) java.lang.Math.floor(v1);
	}

	public static final double floor(double v1) {
		return java.lang.Math.floor(v1);
	}

	public static final <V extends Vec_float<V, ?, ?>> V ceil(V v1) {
		return v1.ceil();
	}

	public static final float ceil(float v1) {
		return (float) java.lang.Math.ceil(v1);
	}

	public static final double ceil(double v1) {
		return java.lang.Math.ceil(v1);
	}

	public static final <V extends Vec_float<V, ?, ?>> V fract(V v1) {
		return v1.fract();
	}

	public static final float fract(float v1) {
		return v1 - floor(v1);
	}

	public static final double fract(double v1) {
		return v1 - floor(v1);
	}

	public static final int min(int v1, int v2) {
		return v1 > v2 ? v2 : v1;
	}

	public static final long min(long v1, long v2) {
		return v1 > v2 ? v2 : v1;
	}

	public static final float min(float v1, float v2) {
		return v1 > v2 ? v2 : v1;
	}

	public static final double min(double v1, double v2) {
		return v1 > v2 ? v2 : v1;
	}

	public static final <V extends Vec_numeric<V, ?, N>, N extends Number> V min(V v1, N v2) {
		return v1.min(v2);
	}

	public static final <V extends Vec_numeric<V, ?, N>, N extends Number> V min(N v1, V v2) {
		return v2.min(v1);
	}

	public static final <V extends Vec_numeric<V, ?, ?>> V min(V v1, V v2) {
		return v1.min(v2);
	}

	public static final <V extends IVec<V, ?>> V min(V v1, int v2) {
		return v1.min(v2);
	}

	public static final <V extends IVec<V, ?>> V min(int v1, V v2) {
		return v2.min(v1);
	}

	public static final <V extends LVec<V, ?>> V min(V v1, long v2) {
		return v1.min(v2);
	}

	public static final <V extends LVec<V, ?>> V min(long v1, V v2) {
		return v2.min(v1);
	}

	public static final <V extends Vec<V, ?>> V min(V v1, float v2) {
		return v1.min(v2);
	}

	public static final <V extends Vec<V, ?>> V min(float v1, V v2) {
		return v2.min(v1);
	}

	public static final <V extends DVec<V, ?>> V min(V v1, double v2) {
		return v1.min(v2);
	}

	public static final <V extends DVec<V, ?>> V min(double v1, V v2) {
		return v2.min(v1);
	}

	public static final int max(int v1, int v2) {
		return v1 > v2 ? v1 : v2;
	}

	public static final long max(long v1, long v2) {
		return v1 > v2 ? v1 : v2;
	}

	public static final float max(float v1, float v2) {
		return v1 > v2 ? v1 : v2;
	}

	public static final double max(double v1, double v2) {
		return v1 > v2 ? v1 : v2;
	}

	public static final <V extends Vec_numeric<V, ?, N>, N extends Number> V max(V v1, N v2) {
		return v1.max(v2);
	}

	public static final <V extends Vec_numeric<V, ?, N>, N extends Number> V max(N v1, V v2) {
		return v2.max(v1);
	}

	public static final <V extends Vec_numeric<V, ?, ?>> V max(V v1, V v2) {
		return v1.max(v2);
	}

	public static final <V extends IVec<V, ?>> V max(V v1, int v2) {
		return v1.max(v2);
	}

	public static final <V extends IVec<V, ?>> V max(int v1, V v2) {
		return v2.max(v1);
	}

	public static final <V extends LVec<V, ?>> V max(V v1, long v2) {
		return v1.max(v2);
	}

	public static final <V extends LVec<V, ?>> V max(long v1, V v2) {
		return v2.max(v1);
	}

	public static final <V extends Vec<V, ?>> V max(V v1, float v2) {
		return v1.max(v2);
	}

	public static final <V extends Vec<V, ?>> V max(float v1, V v2) {
		return v2.max(v1);
	}

	public static final <V extends DVec<V, ?>> V max(V v1, double v2) {
		return v1.max(v2);
	}

	public static final <V extends DVec<V, ?>> V max(double v1, V v2) {
		return v2.max(v1);
	}

	public static final int clamp(int v1, int v2, int v3) {
		return v1 < v2 ? v2 : v1 > v3 ? v3 : v1;
	}

	public static final long clamp(long v1, long v2, long v3) {
		return v1 < v2 ? v2 : v1 > v3 ? v3 : v1;
	}

	public static final float clamp(float v1, float v2, float v3) {
		return v1 < v2 ? v2 : v1 > v3 ? v3 : v1;
	}

	public static final double clamp(double v1, double v2, double v3) {
		return v1 < v2 ? v2 : v1 > v3 ? v3 : v1;
	}

	public static final <V extends Vec_numeric<V, ?, N>, N extends Number> V clamp(V v1, N v2, N v3) {
		return v1.clamp(v2, v3);
	}

	public static final <V extends Vec_numeric<V, ?, ?>> V clamp(V v1, V v2, V v3) {
		return v1.clamp(v2, v3);
	}

	public static final <V extends IVec<V, ?>> V clamp(V v1, int v2, int v3) {
		return v1.clamp(v2, v3);
	}

	public static final <V extends LVec<V, ?>> V clamp(V v1, long v2, long v3) {
		return v1.clamp(v2, v3);
	}

	public static final <V extends Vec<V, ?>> V clamp(V v1, float v2, float v3) {
		return v1.clamp(v2, v3);
	}

	public static final <V extends DVec<V, ?>> V clamp(V v1, double v2, double v3) {
		return v1.clamp(v2, v3);
	}

	public static final float mix(float v1, float v2, float v3) {
		return (1 - v3) * v1 + v3 * v2;
	}

	public static final double mix(double v1, double v2, double v3) {
		return (1 - v3) * v1 + v3 * v2;
	}

	public static final <V extends Vec_float<V, ?, N>, N extends Number> V mix(V v1, V v2, N v3) {
		return v1.mix(v2, v3);
	}

	public static final <V extends Vec_float<V, ?, ?>> V mix(V v1, V v2, V v3) {
		return v1.mix(v2, v3);
	}

	public static final <V extends Vec<V, ?>> V mix(V v1, V v2, float v3) {
		return v1.mix(v2, v3);
	}

	public static final <V extends DVec<V, ?>> V mix(V v1, V v2, double v3) {
		return v1.mix(v2, v3);
	}

	public static final int step(int v1, int v2) {
		return v1 < v2 ? 0 : 1;
	}

	public static final long step(long v1, long v2) {
		return v1 < v2 ? 0 : 1;
	}

	public static final float step(float v1, float v2) {
		return v1 < v2 ? 0 : 1;
	}

	public static final double step(double v1, double v2) {
		return v1 < v2 ? 0 : 1;
	}

	public static final <V extends Vec_numeric<V, ?, N>, N extends Number> V step(V v1, N v2) {
		return v1.step(v2);
	}

	public static final <V extends Vec_numeric<V, ?, ?>> V step(V v1, V v2) {
		return v1.step(v2);
	}

	public static final <V extends IVec<V, ?>> V step(V v1, int v2) {
		return v1.step(v2);
	}

	public static final <V extends LVec<V, ?>> V step(V v1, long v2) {
		return v1.step(v2);
	}

	public static final <V extends Vec<V, ?>> V step(V v1, float v2) {
		return v1.step(v2);
	}

	public static final <V extends DVec<V, ?>> V step(V v1, double v2) {
		return v1.step(v2);
	}

	public static final float smoothstep(float v1, float v2, float v3) {
		final float t = (v1 - v2) / (v3 - v2);
		if (t < 0)
			return 0;
		if (t > 1)
			return 1;
		return t * t * (3.0f - 2.0f * t);
	}

	public static final double smoothstep(double v1, double v2, double v3) {
		final double t = (v1 - v2) / (v3 - v2);
		if (t < 0)
			return 0;
		if (t > 1)
			return 1;
		return t * t * (3.0 - 2.0 * t);
	}

	public static final <V extends Vec_float<V, ?, N>, N extends Number> V smoothstep(V v1, N v2, N v3) {
		return v1.smoothstep(v2, v3);
	}

	public static final <V extends Vec_float<V, ?, ?>> V smoothstep(V v1, V v2, V v3) {
		return v1.smoothstep(v2, v3);
	}

	public static final <V extends Vec<V, ?>> V smoothstep(V v1, float v2, float v3) {
		return v1.smoothstep(v2, v3);
	}

	public static final <V extends DVec<V, ?>> V smoothstep(V v1, double v2, double v3) {
		return v1.smoothstep(v2, v3);
	}

	public static final boolean or(boolean v1, boolean v2) {
		return v1 || v2;
	}

	public static final int or(int v1, int v2) {
		return v1 | v2;
	}

	public static final long or(long v1, long v2) {
		return v1 | v2;
	}

	public static final <V extends Vec_int<V, ?, N>, N extends Number> V or(V v1, N v2) {
		return v1.or(v2);
	}

	public static final <V extends Vec_int<V, ?, N>, N extends Number> V or(N v1, V v2) {
		return v2.or(v1);
	}

	public static final <V extends Vec_int<V, ?, ?>> V or(V v1, V v2) {
		return v1.or(v2);
	}

	public static final <V extends BVec<V>> V or(V v1, V v2) {
		return v1.or(v2);
	}

	public static final <V extends IVec<V, ?>> V or(V v1, int v2) {
		return v1.or(v2);
	}

	public static final <V extends IVec<V, ?>> V or(int v1, V v2) {
		return v2.or(v1);
	}

	public static final <V extends LVec<V, ?>> V or(V v1, long v2) {
		return v1.or(v2);
	}

	public static final <V extends LVec<V, ?>> V or(long v1, V v2) {
		return v2.or(v1);
	}

	public static final <V extends BVec<V>> V or(V v1, boolean v2) {
		return v1.or(v2);
	}

	public static final <V extends BVec<V>> V or(boolean v1, V v2) {
		return v2.or(v1);
	}

	public static final boolean and(boolean v1, boolean v2) {
		return v1 && v2;
	}

	public static final int and(int v1, int v2) {
		return v1 & v2;
	}

	public static final long and(long v1, long v2) {
		return v1 & v2;
	}

	public static final <V extends Vec_int<V, ?, N>, N extends Number> V and(V v1, N v2) {
		return v1.and(v2);
	}

	public static final <V extends Vec_int<V, ?, N>, N extends Number> V and(N v1, V v2) {
		return v2.and(v1);
	}

	public static final <V extends Vec_int<V, ?, ?>> V and(V v1, V v2) {
		return v1.and(v2);
	}

	public static final <V extends BVec<V>> V and(V v1, V v2) {
		return v1.and(v2);
	}

	public static final <V extends IVec<V, ?>> V and(V v1, int v2) {
		return v1.and(v2);
	}

	public static final <V extends IVec<V, ?>> V and(int v1, V v2) {
		return v2.and(v1);
	}

	public static final <V extends LVec<V, ?>> V and(V v1, long v2) {
		return v1.and(v2);
	}

	public static final <V extends LVec<V, ?>> V and(long v1, V v2) {
		return v2.and(v1);
	}

	public static final <V extends BVec<V>> V and(V v1, boolean v2) {
		return v1.and(v2);
	}

	public static final <V extends BVec<V>> V and(boolean v1, V v2) {
		return v2.and(v1);
	}

	public static final boolean xor(boolean v1, boolean v2) {
		return v1 != v2;
	}

	public static final int xor(int v1, int v2) {
		return v1 ^ v2;
	}

	public static final long xor(long v1, long v2) {
		return v1 ^ v2;
	}

	public static final <V extends Vec_int<V, ?, N>, N extends Number> V xor(V v1, N v2) {
		return v1.xor(v2);
	}

	public static final <V extends Vec_int<V, ?, N>, N extends Number> V xor(N v1, V v2) {
		return v2.xor(v1);
	}

	public static final <V extends Vec_int<V, ?, ?>> V xor(V v1, V v2) {
		return v1.xor(v2);
	}

	public static final <V extends BVec<V>> V xor(V v1, V v2) {
		return v1.xor(v2);
	}

	public static final <V extends IVec<V, ?>> V xor(V v1, int v2) {
		return v1.xor(v2);
	}

	public static final <V extends IVec<V, ?>> V xor(int v1, V v2) {
		return v2.xor(v1);
	}

	public static final <V extends LVec<V, ?>> V xor(V v1, long v2) {
		return v1.xor(v2);
	}

	public static final <V extends LVec<V, ?>> V xor(long v1, V v2) {
		return v2.xor(v1);
	}

	public static final <V extends BVec<V>> V xor(V v1, boolean v2) {
		return v1.xor(v2);
	}

	public static final <V extends BVec<V>> V xor(boolean v1, V v2) {
		return v2.xor(v1);
	}

	public static final int shl(int v1, int v2) {
		return v1 << v2;
	}

	public static final long shl(long v1, long v2) {
		return v1 << v2;
	}

	public static final <V extends Vec_int<V, ?, N>, N extends Number> V shl(V v1, N v2) {
		return v1.shl(v2);
	}

	public static final <V extends Vec_int<V, ?, N>, N extends Number> V shl(N v1, V v2) {
		return v2.shl(v1);
	}

	public static final <V extends Vec_int<V, ?, ?>> V shl(V v1, V v2) {
		return v1.shl(v2);
	}

	public static final <V extends IVec<V, ?>> V shl(V v1, int v2) {
		return v1.shl(v2);
	}

	public static final <V extends IVec<V, ?>> V shl(int v1, V v2) {
		return v2.shl(v1);
	}

	public static final <V extends LVec<V, ?>> V shl(V v1, long v2) {
		return v1.shl(v2);
	}

	public static final <V extends LVec<V, ?>> V shl(long v1, V v2) {
		return v2.shl(v1);
	}

	public static final int shr(int v1, int v2) {
		return v1 >> v2;
	}

	public static final long shr(long v1, long v2) {
		return v1 >> v2;
	}

	public static final <V extends Vec_int<V, ?, N>, N extends Number> V shr(V v1, N v2) {
		return v1.shr(v2);
	}

	public static final <V extends Vec_int<V, ?, N>, N extends Number> V shr(N v1, V v2) {
		return v2.shr(v1);
	}

	public static final <V extends Vec_int<V, ?, ?>> V shr(V v1, V v2) {
		return v1.shr(v2);
	}

	public static final <V extends IVec<V, ?>> V shr(V v1, int v2) {
		return v1.shr(v2);
	}

	public static final <V extends IVec<V, ?>> V shr(int v1, V v2) {
		return v2.shr(v1);
	}

	public static final <V extends LVec<V, ?>> V shr(V v1, long v2) {
		return v1.shr(v2);
	}

	public static final <V extends LVec<V, ?>> V shr(long v1, V v2) {
		return v2.shr(v1);
	}

	public static final int ushr(int v1, int v2) {
		return v1 >>> v2;
	}

	public static final long ushr(long v1, long v2) {
		return v1 >>> v2;
	}

	public static final <V extends Vec_int<V, ?, N>, N extends Number> V ushr(V v1, N v2) {
		return v1.ushr(v2);
	}

	public static final <V extends Vec_int<V, ?, N>, N extends Number> V ushr(N v1, V v2) {
		return v2.ushr(v1);
	}

	public static final <V extends Vec_int<V, ?, ?>> V ushr(V v1, V v2) {
		return v1.ushr(v2);
	}

	public static final <V extends IVec<V, ?>> V ushr(V v1, int v2) {
		return v1.ushr(v2);
	}

	public static final <V extends IVec<V, ?>> V ushr(int v1, V v2) {
		return v2.ushr(v1);
	}

	public static final <V extends LVec<V, ?>> V ushr(V v1, long v2) {
		return v1.ushr(v2);
	}

	public static final <V extends LVec<V, ?>> V ushr(long v1, V v2) {
		return v2.ushr(v1);
	}

	public static final <V extends BVec<V>> V invert(V v1) {
		return v1.invert();
	}

	public static final boolean invert(boolean v1) {
		return !v1;
	}

	public static final int invert(int v1) {
		return ~v1;
	}

	public static final long invert(long v1) {
		return ~v1;
	}

	public static final <V extends Vec_numeric<V, ?, ?>> V inc(V v1) {
		return v1.inc();
	}

	public static final int inc(int v1) {
		return v1 + 1;
	}

	public static final long inc(long v1) {
		return v1 + 1;
	}

	public static final float inc(float v1) {
		return v1 + 1;
	}

	public static final double inc(double v1) {
		return v1 + 1;
	}

	public static final <V extends Vec_numeric<V, ?, ?>> V dec(V v1) {
		return v1.dec();
	}

	public static final int dec(int v1) {
		return v1 - 1;
	}

	public static final long dec(long v1) {
		return v1 - 1;
	}

	public static final float dec(float v1) {
		return v1 - 1;
	}

	public static final double dec(double v1) {
		return v1 - 1;
	}

	public static final double length(Vec_numeric<?, ?, ?> v1) {
		return v1.length();
	}

	public static final double length(Quat_base<?, ?> v1) {
		return v1.length();
	}

	public static final <V extends Vec_float<V, ?, ?>> V normalize(V v1) {
		return v1.normalize();
	}

	public static final <Q extends Quat_base<Q, ?>> Q normalize(Q v1) {
		return v1.normalize();
	}

	public static final <V extends Vec_cross<V, ?, ?>> V cross(V v1, V v2) {
		return v1.cross(v2);
	}

	public static final <Q extends Quat_base<Q, ?>> Q cross(Q v1, Q v2) {
		return v1.cross(v2);
	}

	public static final <V extends Vec_numeric<V, ?, ?>> double dot(V v1, V v2) {
		return v1.dot(v2);
	}

	public static final <Q extends Quat_base<Q, ?>> double dot(Q v1, Q v2) {
		return v1.dot(v2);
	}

	public static final <Q extends Quat_base<Q, ?>> Q inverse(Q v1) {
		return v1.inverse();
	}

	public static final boolean any(BVec<?> v1) {
		return v1.any();
	}

	public static final boolean all(BVec<?> v1) {
		return v1.all();
	}

	public static final <V extends Vec_numeric<V, ?, ?>> double distance(V v1, V v2) {
		return v1.distance(v2);
	}

	public static <M extends Mat_base<M, ?, ?, ?>> M transpose(M m) {
		return m.transpose();
	}

	public static <M extends Mat_base<M, M, ?, ?>> M invert(M m) {
		return m.invert();
	}

	public static <M extends Mat_base<M, M, ?, ?>> double det(M m) {
		return m.det();
	}

	public static <M extends Mat_base<M, M, ?, ?>> M adjunkte(M m) {
		return m.adjunkte();
	}

	public static Mat1 outerProduct(Vec1 c, Vec1 r) {
		final float cx = c.x();
		final float rx = r.x();
		return Mat.Mat1(cx * rx);
	}

	public static Mat2 outerProduct(Vec2 c, Vec2 r) {
		final float cx = c.x();
		final float cy = c.y();
		final float rx = r.x();
		final float ry = r.y();

		final float m00 = cx * rx;
		final float m10 = cy * rx;
		final float m01 = cx * ry;
		final float m11 = cy * ry;
		return Mat.Mat2(m00, m10, m01, m11);
	}

	public static Mat3 outerProduct(Vec3 c, Vec3 r) {
		final float cx = c.x();
		final float cy = c.y();
		final float cz = c.z();
		final float rx = r.x();
		final float ry = r.y();
		final float rz = r.z();

		final float m00 = cx * rx;
		final float m10 = cy * rx;
		final float m20 = cz * rx;
		final float m01 = cx * ry;
		final float m11 = cy * ry;
		final float m21 = cz * ry;
		final float m02 = cx * rz;
		final float m12 = cy * rz;
		final float m22 = cz * rz;
		return Mat.Mat3(m00, m10, m20, m01, m11, m21, m02, m12, m22);
	}

	public static Mat4 outerProduct(Vec4 c, Vec4 r) {
		final float cx = c.x();
		final float cy = c.y();
		final float cz = c.z();
		final float cw = c.w();
		final float rx = r.x();
		final float ry = r.y();
		final float rz = r.z();
		final float rw = r.w();
		final float m00 = cx * rx;
		final float m10 = cy * rx;
		final float m20 = cz * rx;
		final float m30 = cw * rx;
		final float m01 = cx * ry;
		final float m11 = cy * ry;
		final float m21 = cz * ry;
		final float m31 = cw * ry;
		final float m02 = cx * rz;
		final float m12 = cy * rz;
		final float m22 = cz * rz;
		final float m32 = cw * rz;
		final float m03 = cx * rw;
		final float m13 = cy * rw;
		final float m23 = cz * rw;
		final float m33 = cw * rw;
		return Mat.Mat4(m00, m10, m20, m30, m01, m11, m21, m31, m02, m12, m22, m32, m03, m13, m23, m33);
	}

	public static MatN outerProduct(VecN c, VecN r) {
		final int size = c.dim();
		if (size != r.dim())
			throw new IllegalArgumentException();
		float[] mat = new float[size * size];
		int k = 0;
		for (int i = 0; i < size; i++) {
			float rg = r.get(i);
			for (int j = 0; j < size; j++) {
				mat[k++] = c.get(j) * rg;
			}
		}
		return Mat.MatX(size, mat);
	}

	public static <V extends Vec_base<V, B, ?>, B extends BVec<B>> B equal(V v1, V v2) {
		return v1.equals(v2);
	}

	public static boolean equal(boolean v1, boolean v2) {
		return v1 == v2;
	}

	public static boolean equal(double v1, double v2) {
		return v1 == v2;
	}

	public static boolean equal(float v1, float v2) {
		return v1 == v2;
	}

	public static boolean equal(int v1, int v2) {
		return v1 == v2;
	}

	public static boolean equal(long v1, long v2) {
		return v1 == v2;
	}

	public static <V extends Vec_base<V, B, ?>, B extends BVec<B>> B notEqual(V v1, V v2) {
		return v1.notEqual(v2);
	}

	public static boolean notEqual(boolean v1, boolean v2) {
		return v1 != v2;
	}

	public static boolean notEqual(double v1, double v2) {
		return v1 != v2;
	}

	public static boolean notEqual(float v1, float v2) {
		return v1 != v2;
	}

	public static boolean notEqual(int v1, int v2) {
		return v1 != v2;
	}

	public static boolean notEqual(long v1, long v2) {
		return v1 != v2;
	}

	public static <V extends Vec_numeric<V, B, ?>, B extends BVec<B>> B smaller(V v1, V v2) {
		return v1.smaller(v2);
	}

	public static boolean smaller(double v1, double v2) {
		return v1 < v2;
	}

	public static boolean smaller(float v1, float v2) {
		return v1 < v2;
	}

	public static boolean smaller(int v1, int v2) {
		return v1 < v2;
	}

	public static boolean smaller(long v1, long v2) {
		return v1 < v2;
	}

	public static <V extends Vec_numeric<V, B, ?>, B extends BVec<B>> B smallerEqual(V v1, V v2) {
		return v1.smallerEqual(v2);
	}

	public static boolean smallerEqual(double v1, double v2) {
		return v1 <= v2;
	}

	public static boolean smallerEqual(float v1, float v2) {
		return v1 <= v2;
	}

	public static boolean smallerEqual(int v1, int v2) {
		return v1 <= v2;
	}

	public static boolean smallerEqual(long v1, long v2) {
		return v1 <= v2;
	}

	public static <V extends Vec_numeric<V, B, ?>, B extends BVec<B>> B bigger(V v1, V v2) {
		return v1.bigger(v2);
	}

	public static boolean bigger(double v1, double v2) {
		return v1 > v2;
	}

	public static boolean bigger(float v1, float v2) {
		return v1 > v2;
	}

	public static boolean bigger(int v1, int v2) {
		return v1 > v2;
	}

	public static boolean bigger(long v1, long v2) {
		return v1 > v2;
	}

	public static <V extends Vec_numeric<V, B, ?>, B extends BVec<B>> B biggerEqual(V v1, V v2) {
		return v1.biggerEqual(v2);
	}

	public static boolean biggerEqual(double v1, double v2) {
		return v1 >= v2;
	}

	public static boolean biggerEqual(float v1, float v2) {
		return v1 >= v2;
	}

	public static boolean biggerEqual(int v1, int v2) {
		return v1 >= v2;
	}

	public static boolean biggerEqual(long v1, long v2) {
		return v1 >= v2;
	}

}
