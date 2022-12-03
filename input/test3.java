package bu.bir.deneme.paketidir;

public class A {
	static void m1() {
		m2();
		B.m2();
	}

	static void m2() {
		B.m1();
	}
  static void m3() {
		B.m1();
	}
  static void m4() {
		B.m1();
	}
  static void m5() {
		B.m1();
	}
  static void emptyMethodA() {}
}

class B {
	static void m1() {
		A.m1();
    B.m2();
    C.m5();
	}

	static void m2() {
		C.m3();
	}
  static void emptyMethodB() {}
}

class C {
	static void m1() {
    B.m3();
  }
  static void m2() {}
}

public class D {
	static void m1() {
    C.m1();
  }
  static void m2() {
    C.m1();
    B.m2();
  }
  static void m3() {
    A.m3();
    B.m2();
  }
}
