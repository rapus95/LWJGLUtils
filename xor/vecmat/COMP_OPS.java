package xor.vecmat;

public final class COMP_OPS {

	private COMP_OPS() {
	}

	public static interface Func1_D {
		double calc(double f);
	}

	public static interface Func2_D {
		double calc(double f1, double f2);
	}

	public static interface Func3_D {
		double calc(double f1, double f2, double f3);
	}

	public static final Func2_D ADD_D = new Func2_D() {
		public @Override double calc(double f1, double f2) {
			return VecMath.add(f1, f2);
		}
	};

	public static final Func2_D SUB_D = new Func2_D() {
		public @Override double calc(double f1, double f2) {
			return VecMath.sub(f1, f2);
		}
	};

	public static final Func2_D SUB2_D = new Func2_D() {
		public @Override double calc(double f1, double f2) {
			return VecMath.sub(f2, f1);
		}
	};

	public static final Func1_D NEG_D = new Func1_D() {
		public @Override double calc(double f1) {
			return VecMath.neg(f1);
		}
	};

	public static final Func2_D MUL_D = new Func2_D() {
		public @Override double calc(double f1, double f2) {
			return VecMath.mul(f1, f2);
		}
	};

	public static final Func2_D DIV_D = new Func2_D() {
		public @Override double calc(double f1, double f2) {
			return VecMath.div(f1, f2);
		}
	};

	public static final Func2_D DIV2_D = new Func2_D() {
		public @Override double calc(double f1, double f2) {
			return VecMath.div(f2, f1);
		}
	};

	public static final Func2_D MOD_D = new Func2_D() {
		public @Override double calc(double f1, double f2) {
			return VecMath.mod(f1, f2);
		}
	};

	public static final Func2_D MOD2_D = new Func2_D() {
		public @Override double calc(double f1, double f2) {
			return VecMath.mod(f2, f1);
		}
	};

	public static final Func2_D POW_D = new Func2_D() {
		public @Override double calc(double f1, double f2) {
			return (double) VecMath.pow(f1, f2);
		}
	};
	
	public static final Func2_D POW2_D = new Func2_D() {
		public @Override double calc(double f1, double f2) {
			return (double) VecMath.pow(f2, f1);
		}
	};

	public static final Func1_D RADIANS_D = new Func1_D() {
		public @Override double calc(double f) {
			return VecMath.radians(f);
		}
	};

	public static final Func1_D DEGREES_D = new Func1_D() {
		public @Override double calc(double f) {
			return VecMath.degrees(f);
		}
	};

	public static final Func1_D SIN_D = new Func1_D() {
		public @Override double calc(double f) {
			return VecMath.sin(f);
		}
	};

	public static final Func1_D COS_D = new Func1_D() {
		public @Override double calc(double f) {
			return (double) VecMath.cos(f);
		}
	};

	public static final Func1_D TAN_D = new Func1_D() {
		public @Override double calc(double f) {
			return (double) VecMath.tan(f);
		}
	};

	public static final Func1_D ASIN_D = new Func1_D() {
		public @Override double calc(double f) {
			return (double) VecMath.asin(f);
		}
	};

	public static final Func1_D ACOS_D = new Func1_D() {
		public @Override double calc(double f) {
			return (double) VecMath.acos(f);
		}
	};

	public static final Func1_D ATAN_D = new Func1_D() {
		public @Override double calc(double f) {
			return (double) VecMath.atan(f);
		}
	};

	public static final Func2_D ATAN2_D = new Func2_D() {
		public @Override double calc(double f1, double f2) {
			return VecMath.atan2(f1, f2);
		}
	};

	public static final Func1_D SINH_D = new Func1_D() {
		public @Override double calc(double f) {
			return VecMath.sinh(f);
		}
	};

	public static final Func1_D COSH_D = new Func1_D() {
		public @Override double calc(double f) {
			return VecMath.cosh(f);
		}
	};

	public static final Func1_D TANH_D = new Func1_D() {
		public @Override double calc(double f) {
			return VecMath.tanh(f);
		}
	};

	public static final Func1_D ASINH_D = new Func1_D() {
		public @Override double calc(double f) {
			return VecMath.asinh(f);
		}
	};

	public static final Func1_D ACOSH_D = new Func1_D() {
		public @Override double calc(double f) {
			return VecMath.acosh(f);
		}
	};

	public static final Func1_D ATANH_D = new Func1_D() {
		public @Override double calc(double f) {
			return VecMath.atanh(f);
		}
	};

	public static final Func1_D EXP_D = new Func1_D() {
		public @Override double calc(double f) {
			return VecMath.exp(f);
		}
	};

	public static final Func1_D LOG_D = new Func1_D() {
		public @Override double calc(double f) {
			return VecMath.log(f);
		}
	};

	public static final Func1_D EXP2_D = new Func1_D() {
		public @Override double calc(double f) {
			return VecMath.exp2(f);
		}
	};

	public static final Func1_D LOG2_D = new Func1_D() {
		public @Override double calc(double f) {
			return VecMath.log2(f);
		}
	};

	public static final Func1_D SQRT_D = new Func1_D() {
		public @Override double calc(double f) {
			return VecMath.sqrt(f);
		}
	};

	public static final Func1_D INVERSESQRT_D = new Func1_D() {
		public @Override double calc(double f) {
			return VecMath.inversesqrt(f);
		}
	};

	public static final Func1_D ABS_D = new Func1_D() {
		public @Override double calc(double f) {
			return VecMath.abs(f);
		}
	};

	public static final Func1_D SIGN_D = new Func1_D() {
		public @Override double calc(double f) {
			return VecMath.sign(f);
		}
	};

	public static final Func1_D ROUNDEVEN_D = new Func1_D() {
		public @Override double calc(double f) {
			return VecMath.roundeven(f);
		}
	};

	public static final Func1_D ROUND_D = new Func1_D() {
		public @Override double calc(double f) {
			return VecMath.round(f);
		}
	};

	public static final Func1_D TRUNC_D = new Func1_D() {
		public @Override double calc(double f) {
			return VecMath.trunc(f);
		}
	};

	public static final Func1_D FLOOR_D = new Func1_D() {
		public @Override double calc(double f) {
			return VecMath.floor(f);
		}
	};

	public static final Func1_D CEIL_D = new Func1_D() {
		public @Override double calc(double f) {
			return VecMath.ceil(f);
		}
	};

	public static final Func1_D FRACT_D = new Func1_D() {
		public @Override double calc(double f) {
			return VecMath.fract(f);
		}
	};

	public static final Func2_D MIN_D = new Func2_D() {
		public @Override double calc(double f1, double f2) {
			return VecMath.min(f1, f2);
		}
	};

	public static final Func2_D MAX_D = new Func2_D() {
		public @Override double calc(double f1, double f2) {
			return VecMath.max(f1, f2);
		}
	};

	public static final Func3_D CLAMP_D = new Func3_D() {
		public @Override double calc(double f1, double f2, double f3) {
			return VecMath.clamp(f1, f2, f3);
		}
	};

	public static final Func3_D MIX_D = new Func3_D() {
		public @Override double calc(double f1, double f2, double f3) {
			return VecMath.mix(f1, f2, f3);
		}
	};

	public static final Func2_D STEP_D = new Func2_D() {
		public @Override double calc(double f1, double f2) {
			return VecMath.step(f1, f2);
		}
	};

	public static final Func3_D SMOOTHSTEP_D = new Func3_D() {
		public @Override double calc(double f1, double f2, double f3) {
			return VecMath.smoothstep(f1, f2, f3);
		}
	};

	public static final Func3_D ADD_SCALED_D = new Func3_D() {
		public @Override double calc(double f1, double f2, double f3) {
			return VecMath.add_scaled(f1, f2, f3);
		}
	};

	public static final Func1_D INC_D = new Func1_D() {
		public @Override double calc(double f1) {
			return VecMath.inc(f1);
		}
	};
	
	public static final Func1_D DEC_D = new Func1_D() {
		public @Override double calc(double f1) {
			return VecMath.dec(f1);
		}
	};
	
	public static interface Func1_F {
		float calc(float f);
	}

	public static interface Func2_F {
		float calc(float f1, float f2);
	}

	public static interface Func3_F {
		float calc(float f1, float f2, float f3);
	}

	public static final Func2_F ADD_F = new Func2_F() {
		public @Override float calc(float f1, float f2) {
			return VecMath.add(f1, f2);
		}
	};

	public static final Func2_F SUB_F = new Func2_F() {
		public @Override float calc(float f1, float f2) {
			return VecMath.sub(f1, f2);
		}
	};

	public static final Func2_F SUB2_F = new Func2_F() {
		public @Override float calc(float f1, float f2) {
			return VecMath.sub(f2, f1);
		}
	};

	public static final Func1_F NEG_F = new Func1_F() {
		public @Override float calc(float f1) {
			return VecMath.neg(f1);
		}
	};

	public static final Func2_F MUL_F = new Func2_F() {
		public @Override float calc(float f1, float f2) {
			return VecMath.mul(f1, f2);
		}
	};

	public static final Func2_F DIV_F = new Func2_F() {
		public @Override float calc(float f1, float f2) {
			return VecMath.div(f1, f2);
		}
	};

	public static final Func2_F DIV2_F = new Func2_F() {
		public @Override float calc(float f1, float f2) {
			return VecMath.div(f2, f1);
		}
	};

	public static final Func2_F MOD_F = new Func2_F() {
		public @Override float calc(float f1, float f2) {
			return VecMath.mod(f1, f2);
		}
	};

	public static final Func2_F MOD2_F = new Func2_F() {
		public @Override float calc(float f1, float f2) {
			return VecMath.mod(f2, f1);
		}
	};

	public static final Func2_F POW_F = new Func2_F() {
		public @Override float calc(float f1, float f2) {
			return (float) VecMath.pow(f1, f2);
		}
	};

	public static final Func2_F POW2_F = new Func2_F() {
		public @Override float calc(float f1, float f2) {
			return VecMath.pow(f2, f1);
		}
	};
	
	public static final Func1_F RADIANS_F = new Func1_F() {
		public @Override float calc(float f) {
			return VecMath.radians(f);
		}
	};

	public static final Func1_F DEGREES_F = new Func1_F() {
		public @Override float calc(float f) {
			return VecMath.degrees(f);
		}
	};

	public static final Func1_F SIN_F = new Func1_F() {
		public @Override float calc(float f) {
			return VecMath.sin(f);
		}
	};

	public static final Func1_F COS_F = new Func1_F() {
		public @Override float calc(float f) {
			return (float) VecMath.cos(f);
		}
	};

	public static final Func1_F TAN_F = new Func1_F() {
		public @Override float calc(float f) {
			return (float) VecMath.tan(f);
		}
	};

	public static final Func1_F ASIN_F = new Func1_F() {
		public @Override float calc(float f) {
			return (float) VecMath.asin(f);
		}
	};

	public static final Func1_F ACOS_F = new Func1_F() {
		public @Override float calc(float f) {
			return (float) VecMath.acos(f);
		}
	};

	public static final Func1_F ATAN_F = new Func1_F() {
		public @Override float calc(float f) {
			return (float) VecMath.atan(f);
		}
	};

	public static final Func2_F ATAN2_F = new Func2_F() {
		public @Override float calc(float f1, float f2) {
			return VecMath.atan2(f1, f2);
		}
	};

	public static final Func1_F SINH_F = new Func1_F() {
		public @Override float calc(float f) {
			return VecMath.sinh(f);
		}
	};

	public static final Func1_F COSH_F = new Func1_F() {
		public @Override float calc(float f) {
			return VecMath.cosh(f);
		}
	};

	public static final Func1_F TANH_F = new Func1_F() {
		public @Override float calc(float f) {
			return VecMath.tanh(f);
		}
	};

	public static final Func1_F ASINH_F = new Func1_F() {
		public @Override float calc(float f) {
			return VecMath.asinh(f);
		}
	};

	public static final Func1_F ACOSH_F = new Func1_F() {
		public @Override float calc(float f) {
			return VecMath.acosh(f);
		}
	};

	public static final Func1_F ATANH_F = new Func1_F() {
		public @Override float calc(float f) {
			return VecMath.atanh(f);
		}
	};

	public static final Func1_F EXP_F = new Func1_F() {
		public @Override float calc(float f) {
			return VecMath.exp(f);
		}
	};

	public static final Func1_F LOG_F = new Func1_F() {
		public @Override float calc(float f) {
			return VecMath.log(f);
		}
	};

	public static final Func1_F EXP2_F = new Func1_F() {
		public @Override float calc(float f) {
			return VecMath.exp2(f);
		}
	};

	public static final Func1_F LOG2_F = new Func1_F() {
		public @Override float calc(float f) {
			return VecMath.log2(f);
		}
	};

	public static final Func1_F SQRT_F = new Func1_F() {
		public @Override float calc(float f) {
			return VecMath.sqrt(f);
		}
	};

	public static final Func1_F INVERSESQRT_F = new Func1_F() {
		public @Override float calc(float f) {
			return VecMath.inversesqrt(f);
		}
	};

	public static final Func1_F ABS_F = new Func1_F() {
		public @Override float calc(float f) {
			return VecMath.abs(f);
		}
	};

	public static final Func1_F SIGN_F = new Func1_F() {
		public @Override float calc(float f) {
			return VecMath.sign(f);
		}
	};

	public static final Func1_F ROUNDEVEN_F = new Func1_F() {
		public @Override float calc(float f) {
			return VecMath.roundeven(f);
		}
	};

	public static final Func1_F ROUND_F = new Func1_F() {
		public @Override float calc(float f) {
			return VecMath.round(f);
		}
	};

	public static final Func1_F TRUNC_F = new Func1_F() {
		public @Override float calc(float f) {
			return VecMath.trunc(f);
		}
	};

	public static final Func1_F FLOOR_F = new Func1_F() {
		public @Override float calc(float f) {
			return VecMath.floor(f);
		}
	};

	public static final Func1_F CEIL_F = new Func1_F() {
		public @Override float calc(float f) {
			return VecMath.ceil(f);
		}
	};

	public static final Func1_F FRACT_F = new Func1_F() {
		public @Override float calc(float f) {
			return VecMath.fract(f);
		}
	};

	public static final Func2_F MIN_F = new Func2_F() {
		public @Override float calc(float f1, float f2) {
			return VecMath.min(f1, f2);
		}
	};

	public static final Func2_F MAX_F = new Func2_F() {
		public @Override float calc(float f1, float f2) {
			return VecMath.max(f1, f2);
		}
	};

	public static final Func3_F CLAMP_F = new Func3_F() {
		public @Override float calc(float f1, float f2, float f3) {
			return VecMath.clamp(f1, f2, f3);
		}
	};

	public static final Func3_F MIX_F = new Func3_F() {
		public @Override float calc(float f1, float f2, float f3) {
			return VecMath.mix(f1, f2, f3);
		}
	};

	public static final Func2_F STEP_F = new Func2_F() {
		public @Override float calc(float f1, float f2) {
			return VecMath.step(f1, f2);
		}
	};

	public static final Func3_F SMOOTHSTEP_F = new Func3_F() {
		public @Override float calc(float f1, float f2, float f3) {
			return VecMath.smoothstep(f1, f2, f3);
		}
	};

	public static final Func3_F ADD_SCALED_F = new Func3_F() {
		public @Override float calc(float f1, float f2, float f3) {
			return VecMath.add_scaled(f1, f2, f3);
		}
	};
	
	public static final Func1_F INC_F = new Func1_F() {
		public @Override float calc(float f1) {
			return VecMath.inc(f1);
		}
	};
	
	public static final Func1_F DEC_F = new Func1_F() {
		public @Override float calc(float f1) {
			return VecMath.dec(f1);
		}
	};
	
	public static interface Func1_L {
		long calc(long f);
	}

	public static interface Func2_L {
		long calc(long f1, long f2);
	}

	public static interface Func3_L {
		long calc(long f1, long f2, long f3);
	}

	public static final Func2_L ADD_L = new Func2_L() {
		public @Override long calc(long f1, long f2) {
			return VecMath.add(f1, f2);
		}
	};

	public static final Func2_L SUB_L = new Func2_L() {
		public @Override long calc(long f1, long f2) {
			return VecMath.sub(f1, f2);
		}
	};

	public static final Func2_L SUB2_L = new Func2_L() {
		public @Override long calc(long f1, long f2) {
			return VecMath.sub(f2, f1);
		}
	};

	public static final Func1_L NEG_L = new Func1_L() {
		public @Override long calc(long f1) {
			return VecMath.neg(f1);
		}
	};

	public static final Func2_L MUL_L = new Func2_L() {
		public @Override long calc(long f1, long f2) {
			return VecMath.mul(f1, f2);
		}
	};

	public static final Func2_L DIV_L = new Func2_L() {
		public @Override long calc(long f1, long f2) {
			return VecMath.idiv(f1, f2);
		}
	};

	public static final Func2_L DIV2_L = new Func2_L() {
		public @Override long calc(long f1, long f2) {
			return VecMath.idiv(f2, f1);
		}
	};

	public static final Func2_L MOD_L = new Func2_L() {
		public @Override long calc(long f1, long f2) {
			return VecMath.mod(f1, f2);
		}
	};

	public static final Func2_L MOD2_L = new Func2_L() {
		public @Override long calc(long f1, long f2) {
			return VecMath.mod(f2, f1);
		}
	};

	public static final Func2_L POW_L = new Func2_L() {
		public @Override long calc(long f1, long f2) {
			return (long) VecMath.pow(f1, f2);
		}
	};
	
	public static final Func2_L POW2_L = new Func2_L() {
		public @Override long calc(long f1, long f2) {
			return (long) VecMath.pow(f2, f1);
		}
	};

	public static final Func1_L ABS_L = new Func1_L() {
		public @Override long calc(long f) {
			return VecMath.abs(f);
		}
	};

	public static final Func1_L SIGN_L = new Func1_L() {
		public @Override long calc(long f) {
			return VecMath.sign(f);
		}
	};

	public static final Func2_L MIN_L = new Func2_L() {
		public @Override long calc(long f1, long f2) {
			return VecMath.min(f1, f2);
		}
	};

	public static final Func2_L MAX_L = new Func2_L() {
		public @Override long calc(long f1, long f2) {
			return VecMath.max(f1, f2);
		}
	};

	public static final Func3_L CLAMP_L = new Func3_L() {
		public @Override long calc(long f1, long f2, long f3) {
			return VecMath.clamp(f1, f2, f3);
		}
	};

	public static final Func2_L STEP_L = new Func2_L() {
		public @Override long calc(long f1, long f2) {
			return VecMath.step(f1, f2);
		}
	};
	
	public static final Func2_L OR_L = new Func2_L() {
		public @Override long calc(long f1, long f2) {
			return VecMath.or(f1, f2);
		}
	};
	
	public static final Func2_L AND_L = new Func2_L() {
		public @Override long calc(long f1, long f2) {
			return VecMath.and(f1, f2);
		}
	};
	
	public static final Func2_L XOR_L = new Func2_L() {
		public @Override long calc(long f1, long f2) {
			return VecMath.xor(f1, f2);
		}
	};
	
	public static final Func2_L SHL_L = new Func2_L() {
		public @Override long calc(long f1, long f2) {
			return VecMath.shl(f1, f2);
		}
	};
	
	public static final Func2_L SHL2_L = new Func2_L() {
		public @Override long calc(long f1, long f2) {
			return VecMath.shl(f2, f1);
		}
	};
	
	public static final Func2_L SHR_L = new Func2_L() {
		public @Override long calc(long f1, long f2) {
			return VecMath.shr(f1, f2);
		}
	};
	
	public static final Func2_L SHR2_L = new Func2_L() {
		public @Override long calc(long f1, long f2) {
			return VecMath.shr(f2, f1);
		}
	};
	
	public static final Func2_L USHR_L = new Func2_L() {
		public @Override long calc(long f1, long f2) {
			return VecMath.ushr(f1, f2);
		}
	};
	
	public static final Func2_L USHR2_L = new Func2_L() {
		public @Override long calc(long f1, long f2) {
			return VecMath.ushr(f2, f1);
		}
	};
	
	public static final Func1_L INVERT_L = new Func1_L() {
		public @Override long calc(long f1) {
			return VecMath.invert(f1);
		}
	};
	
	public static final Func1_L INC_L = new Func1_L() {
		public @Override long calc(long f1) {
			return VecMath.inc(f1);
		}
	};
	
	public static final Func1_L DEC_L = new Func1_L() {
		public @Override long calc(long f1) {
			return VecMath.dec(f1);
		}
	};
	
	public static interface Func1_I {
		int calc(int f);
	}

	public static interface Func2_I {
		int calc(int f1, int f2);
	}

	public static interface Func3_I {
		int calc(int f1, int f2, int f3);
	}

	public static final Func2_I ADD_I = new Func2_I() {
		public @Override int calc(int f1, int f2) {
			return VecMath.add(f1, f2);
		}
	};

	public static final Func2_I SUB_I = new Func2_I() {
		public @Override int calc(int f1, int f2) {
			return VecMath.sub(f1, f2);
		}
	};

	public static final Func2_I SUB2_I = new Func2_I() {
		public @Override int calc(int f1, int f2) {
			return VecMath.sub(f2, f1);
		}
	};

	public static final Func1_I NEG_I = new Func1_I() {
		public @Override int calc(int f1) {
			return VecMath.neg(f1);
		}
	};

	public static final Func2_I MUL_I = new Func2_I() {
		public @Override int calc(int f1, int f2) {
			return VecMath.mul(f1, f2);
		}
	};

	public static final Func2_I DIV_I = new Func2_I() {
		public @Override int calc(int f1, int f2) {
			return VecMath.idiv(f1, f2);
		}
	};

	public static final Func2_I DIV2_I = new Func2_I() {
		public @Override int calc(int f1, int f2) {
			return VecMath.idiv(f2, f1);
		}
	};

	public static final Func2_I MOD_I = new Func2_I() {
		public @Override int calc(int f1, int f2) {
			return VecMath.mod(f1, f2);
		}
	};

	public static final Func2_I MOD2_I = new Func2_I() {
		public @Override int calc(int f1, int f2) {
			return VecMath.mod(f2, f1);
		}
	};

	public static final Func2_I POW_I = new Func2_I() {
		public @Override int calc(int f1, int f2) {
			return (int) VecMath.pow(f1, f2);
		}
	};
	
	public static final Func2_I POW2_I = new Func2_I() {
		public @Override int calc(int f1, int f2) {
			return (int) VecMath.pow(f2, f1);
		}
	};

	public static final Func1_I ABS_I = new Func1_I() {
		public @Override int calc(int f) {
			return VecMath.abs(f);
		}
	};

	public static final Func1_I SIGN_I = new Func1_I() {
		public @Override int calc(int f) {
			return VecMath.sign(f);
		}
	};

	public static final Func2_I MIN_I = new Func2_I() {
		public @Override int calc(int f1, int f2) {
			return VecMath.min(f1, f2);
		}
	};

	public static final Func2_I MAX_I = new Func2_I() {
		public @Override int calc(int f1, int f2) {
			return VecMath.max(f1, f2);
		}
	};

	public static final Func3_I CLAMP_I = new Func3_I() {
		public @Override int calc(int f1, int f2, int f3) {
			return VecMath.clamp(f1, f2, f3);
		}
	};

	public static final Func2_I STEP_I = new Func2_I() {
		public @Override int calc(int f1, int f2) {
			return VecMath.step(f1, f2);
		}
	};
	
	public static final Func2_I OR_I = new Func2_I() {
		public @Override int calc(int f1, int f2) {
			return VecMath.or(f1, f2);
		}
	};
	
	public static final Func2_I AND_I = new Func2_I() {
		public @Override int calc(int f1, int f2) {
			return VecMath.and(f1, f2);
		}
	};
	
	public static final Func2_I XOR_I = new Func2_I() {
		public @Override int calc(int f1, int f2) {
			return VecMath.xor(f1, f2);
		}
	};
	
	public static final Func2_I SHL_I = new Func2_I() {
		public @Override int calc(int f1, int f2) {
			return VecMath.shl(f1, f2);
		}
	};
	
	public static final Func2_I SHL2_I = new Func2_I() {
		public @Override int calc(int f1, int f2) {
			return VecMath.shl(f2, f1);
		}
	};
	
	public static final Func2_I SHR_I = new Func2_I() {
		public @Override int calc(int f1, int f2) {
			return VecMath.shr(f1, f2);
		}
	};
	
	public static final Func2_I SHR2_I = new Func2_I() {
		public @Override int calc(int f1, int f2) {
			return VecMath.shr(f2, f1);
		}
	};
	
	public static final Func2_I USHR_I = new Func2_I() {
		public @Override int calc(int f1, int f2) {
			return VecMath.ushr(f1, f2);
		}
	};
	
	public static final Func2_I USHR2_I = new Func2_I() {
		public @Override int calc(int f1, int f2) {
			return VecMath.ushr(f2, f1);
		}
	};
	
	public static final Func1_I INVERT_I = new Func1_I() {
		public @Override int calc(int f1) {
			return VecMath.invert(f1);
		}
	};
	
	public static final Func1_I INC_I = new Func1_I() {
		public @Override int calc(int f1) {
			return VecMath.inc(f1);
		}
	};
	
	public static final Func1_I DEC_I = new Func1_I() {
		public @Override int calc(int f1) {
			return VecMath.dec(f1);
		}
	};
	
	public static interface Func1_B {
		boolean calc(boolean f);
	}

	public static interface Func2_B {
		boolean calc(boolean f1, boolean f2);
	}

	public static interface Func3_B {
		boolean calc(boolean f1, boolean f2, boolean f3);
	}
	
	public static final Func2_B OR_B = new Func2_B() {
		public @Override boolean calc(boolean f1, boolean f2) {
			return VecMath.or(f1, f2);
		}
	};
	
	public static final Func2_B AND_B = new Func2_B() {
		public @Override boolean calc(boolean f1, boolean f2) {
			return VecMath.and(f1, f2);
		}
	};
	
	public static final Func2_B XOR_B = new Func2_B() {
		public @Override boolean calc(boolean f1, boolean f2) {
			return VecMath.xor(f1, f2);
		}
	};
	
	public static final Func1_B INVERT_B = new Func1_B() {
		public @Override boolean calc(boolean f1) {
			return VecMath.invert(f1);
		}
	};
	
}
