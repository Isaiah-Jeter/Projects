document.getElementsByTagName("h1")[0].style.fontSize = "80px";
document.getElementsByTagName("a")[0].style.fontSize="30px";
document.getElementsByTagName("a")[1].style.fontSize="30px";
document.getElementsByTagName("a")[2].style.fontSize="30px"
document.getElementsByTagName("a")[3].style.fontSize="30px";

homeweb.classList.toggle('is-hidden')

why.onclick=function(){
  console.log("hit")
  if(!rsarsa.classList.contains("is-hidden")){
    rsarsa.classList.toggle("is-hidden")
  }
  if(!homeweb.classList.contains("is-hidden")){
    homeweb.classList.toggle("is-hidden")
  }
  if(!WWUE.classList.contains("is-hidden")){
    WWUE.classList.toggle("is-hidden")
  }
  if(!Cypher.classList.contains("is-hidden")){
    Cypher.classList.toggle("is-hidden")
  }
  WWUE.classList.toggle("is-hidden")
  
  why.classList.add("is-active")
}


rsa.onclick=function(){
  console.log("hit")
  if(!rsarsa.classList.contains("is-hidden")){
    rsarsa.classList.toggle("is-hidden")
  }
  if(!homeweb.classList.contains("is-hidden")){
    homeweb.classList.toggle("is-hidden")
  }
  if(!WWUE.classList.contains("is-hidden")){
    WWUE.classList.toggle("is-hidden")
  }
  if(!Cypher.classList.contains("is-hidden")){
    Cypher.classList.toggle("is-hidden")
  }
  rsarsa.classList.toggle("is-hidden")
  rsa.classList.add("is-active")
}

CC.onclick=function(){
  console.log("hit")
  if(!rsarsa.classList.contains("is-hidden")){
    rsarsa.classList.toggle("is-hidden")
  }
  if(!homeweb.classList.contains("is-hidden")){
    homeweb.classList.toggle("is-hidden")
  }
  if(!WWUE.classList.contains("is-hidden")){
    WWUE.classList.toggle("is-hidden")
  }
  if(!Cypher.classList.contains("is-hidden")){
    Cypher.classList.toggle("is-hidden")
  }
  Cypher.classList.toggle("is-hidden")
  CC.classList.add("is-active")
  
}

 home.onclick=function(){
  console.log("hit")
  if(!rsarsa.classList.contains("is-hidden")){
    rsarsa.classList.toggle("is-hidden")
  }
  if(!homeweb.classList.contains("is-hidden")){
    homeweb.classList.toggle("is-hidden")
  }
  if(!WWUE.classList.contains("is-hidden")){
    WWUE.classList.toggle("is-hidden")
  }
  if(!Cypher.classList.contains("is-hidden")){
    Cypher.classList.toggle("is-hidden")
  }
  homeweb.classList.toggle("is-hidden")
  home.classList.add("is-active")
}

var n = 0;
var phin = 0;
var x = 0;
var encrypted = 0;
var d0 = 0;
var d1 = 0;
var d2 = 0;
var d3 = 0;
var d4 = 0;
var d5 = 0;
var d6 = 0;
var d7 = 0;
var d8 = 0;
var d9 = 0;


pq = function(){
  if (isPrime(p.value)){
    if (isPrime(q.value)){
      n = p.value * q.value
      console.log(n);
      document.getElementById("message").innerHTML = n;
    }
    else {
    document.getElementById("message").innerHTML = "Please enter two prime numbers";
    }
  }
  else {
    document.getElementById("message").innerHTML = "Please enter two prime numbers";
  }
}

const isPrime = num => {
    for(let i = 2, s = Math.sqrt(num); i <= s; i++){
        if(num % i === 0) return false; 
    }
    return num > 1;
}

phi = function () {
  phin = (p.value-1) * (q.value-1)
  document.getElementById("phi").innerHTML = phin;
}

function gcd (a, b) {
  if (!b) {
    return a;
  }
  return gcd(b, a % b);
}

checke = function(){
  if (gcd(e.value,phin) === 1) {
    document.getElementById("coprime").innerHTML = "Good e is coprime"
  }
  else {
    document.getElementById("coprime").innerHTML = "e is not coprime. Please choose a different e."
  }
    
}

mod = function(){
  x = modinput.value%modmod.value
  console.log(x)
  document.getElementById("modreturn").innerHTML = x;
}
   
var a = 1
var d = 0

evald = function(){
    while (true){
      var current = phin * (a+1)
      if (current%e.value===0){
        d = current/e.value
        console.log(d)
        document.getElementById("dreturn").innerHTML = d
        break;
      }
      console.log(a);
      a = a+1;
  }
}
var string
function basefun (base) {
  d0 = plaintext.value%base;
  d1 = math.floor(plaintext.value/base)%base;
  d2 = math.floor(plaintext.value/Math.pow(base,2))%base;
  d3 = math.floor(plaintext.value/Math.pow(base,3))%base;
  d4 = math.floor(plaintext.value/Math.pow(base,4))%base;
  d5 = math.floor(plaintext.value/Math.pow(base,5))%base;
  d6 = math.floor(plaintext.value/Math.pow(base,6))%base;
  d7 = math.floor(plaintext.value/Math.pow(base,7))%base;
  d8 = math.floor(plaintext.value/Math.pow(base,8))%base;
  d9 = math.floor(plaintext.value/Math.pow(base,9))%base;
  string = String(d9)+" "+String(d8)+" "+String(d7)+" "+String(d6)+" "+String(d5)+" "+String(d4)+" "+String(d3)+" "+String(d2)+" "+String(d1)+" "+String(d0)
  document.getElementById("chunked").innerHTML = string;
}

encrypt = function (plain){
  return (Math.pow(plain,e.value))%n
}


function encryptall () {
  basefun(200);
  e0 = encrypt(d0)
  e1 = encrypt(d1)
  e2 = encrypt(d2)
  e3 = encrypt(d3)
  e4 = encrypt(d4)
  e5 = encrypt(d5)
  e6 = encrypt(d6)
  e7 = encrypt(d7)
  e8 = encrypt(d8)
  e9 = encrypt(d9)
  document.getElementById("cipherout").innerHTML = String(e9)+" "+String(e8)+" "+String(e7)+" "+String(e6)+" "+String(e5)+" "+String(e4)+" "+String(e3)+" "+String(e2)+" "+String(e1)+" "+String(e0);
}

var back = 1;
var b = 0;

function decrypt() {
  document.getElementById("decrypted").innerHTML = string;
  document.getElementById("decryptedplaintext").innerHTML = plaintext.value;
}
 

var shift = function(str, amount) {
amount = parseInt(amount);
	// Wrap the amount
	if (amount < 0)
		return caesarShift(str, amount + 26);

	// Make an output variable
	var output = '';

	// Go through each character
	for (var i = 0; i < str.length; i ++) {

		// Get the character we'll be appending
		var c = str[i];
    console.log(str[i])

		// If it's a letter...
		if (c.match(/[a-z]/i)) {

			// Get its code
			var code = str.charCodeAt(i);

			// Uppercase letters
			if ((code>=65)&&(code<=90))
				c = String.fromCharCode(((code-65+amount) %26)+65);

			// Lowercase letters
			else if ((code>=97) && (code <= 122))
				c = String.fromCharCode(((code-97+amount)%26)+97);

		}

		// Append
		output += c;

	}

	document.getElementById("shiftReturn").innerHTML =output ;
  return output;
};