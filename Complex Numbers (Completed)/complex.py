class Complex:

    def __init__ (self, real, imag):
        '''
        Creates an instance of the Complex class with real and imaginary components
        '''
        self.real=real
        self.imag=imag

    def __str__(self):
        '''
        Returns a string representation of self 
        '''
        return f"{self.real:.2f} + {self.imag:.2f}i"

    def add(self, other):
        ''' 
        Returns the result of adding two complex numbers
        '''
        return Complex((self.real+other.real),(self.imag+other.imag))

    def conjugate(self):
        ''' 
        Returns the conjugate of self, the complex number
        '''
        return Complex(self.real,(-1*self.imag))

    def divide(self,other):
        ''' 
        Returns the difference of two complex numbers
        '''
        numReal=(self.real*other.real)+(self.imag*other.imag)
        denomReal=(other.real)**2+(other.imag)**2
        numImag=(self.imag*other.real)-(self.real*other.imag)
        denomImag=(other.real)**2+(other.imag)**2
        return Complex((numReal/denomReal),(numImag/denomImag))


    def magnitude(self):
        ''' 
        returns distance of the complex number from the origin
        '''
        return ((self.real)**2+(self.imag)**2)**(1/2)

    def multiply(self, other):
        '''
        Input two complex numbers
        Returns the result of multiplying the numbers
        '''
        realPart=(self.real*other.real)-(self.imag*other.imag)
        imagPart=(self.real*other.imag)+(self.imag*other.real)
        return Complex(realPart,imagPart)

    def negate(self):
        '''
        Input a complex number 
        Returns the negation of the complex number 
        '''
        return Complex(-self.real, -self.imag)

    def reciprocal(self):
        '''
        Inputs a complex number (self)
        Outputs the reciprocol of (self), 1/self
        Throws a ZeroExceptionError if self is 0
        '''
        a = self.real
        b = self.imag
        c = a*a + b*b
        if(c==0):
            raise ZeroDivisionError
        else:
            return Complex((a/c), (-b/c))
        
    def subtract(self, other):
        '''
        Returns the difference between two complex numbers as a new complex number
        '''
        return Complex((self.real-other.real),(self.imag-other.imag))
