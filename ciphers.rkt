#lang racket

(require "functions.rkt")
(provide message->plaintext substitution-cipher columnar-transposition-cipher caesar-cipher)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; message->plaintext
; ------------------------------------------------------------------------------
; Prepares a message to encrypt / decrypt
;
; inputs: 
;   plaintext: A string
; outputs: 
;   The plaintext stripped of any characters that are not
;   a letter; all letters are converted to lowercase
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(define (message->plaintext plaintext)
  (list->string(downcase(alphabetic-only (string->list plaintext)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; substitution-cipher
; ------------------------------------------------------------------------------
; Creates a function that can be used as a substitution cipher on a string
;
; inputs: 
;   substituter: A function that takes a character and returns a character, in
;                effect substituting the input character for the result
;                character
; outputs: 
;   A function that takes a string and returns a new string where each character
;   in the new string has been subsitituted according to the substituter.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(define (substitution-cipher substituter)
  (lambda (string) (list->string(map substituter (string->list string)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; caesar-substituter
; ------------------------------------------------------------------------------
; A substitution function for a Caesar shift
;
; inputs:
;   amount: A number (between 0 and 25) to shift the alphabet
;   
; outputs: 
;   A substitution function that takes a character and shifts it by the
;   specified amount.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(define (caesar-substituter amount)
  (lambda (character)
    (let* ([n_c (char->integer character)] ; numeric value for plaintext char
           [n_a (char->integer #\a)])      ; numeric value 'a'
      ; compute the numeric value for the ciphertext char as:
      ;   n_a + ((n_c - n_a + amount) % 26)
      ; then, convert that numeric value to a character
      (integer->char (+ n_a (modulo (+ (- n_c n_a) amount) 26))))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; caesar-cipher
; ------------------------------------------------------------------------------
; Creates a function that can perform a Caesar substitution cipher on a string.
;
; inputs: 
;   amount: A number (between 0 and 25) to shift the alphabet
; outputs: 
;   A substitution cipher that takes a plaintext string and returns a ciphertext
;   string encoding by shifting the characters in the string by the specified
;   amount.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(define (caesar-cipher amount)
  (lambda (string) (list->string(map (caesar-substituter amount) (string->list string)))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; pad-message
; ------------------------------------------------------------------------------
; Given a string and an expected length, creates a new string that is extended
; with random characters until it is the expected length.
;
; If the expected length is less than or equal to the given string’s length,
; then returns the orginal string.
;
; inputs: 
;   message: A string
;   expected-length: A non-negative integer
; outputs: 
;   A string of expected-length, created by padding the given string with 0 or
;   more random characters.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(define (pad-message message expected-length)
  ; If the expected length is shorter than the message, then the amount of
  ; padding we need is 0 characters; otherwise, it's the difference between the
  ; expected length and the message length.
  (let* ([message-length (string-length message)]
         [pad-difference (- expected-length message-length)]
         [padding-length (max 0 pad-difference)]
         [padding (list->string (random-chars padding-length))])
    (string-append message padding)))

; A helper function that produces `length` random characters
(define (random-chars length)
  (map (lambda (i) (random-char)) (range length)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; columnar-transposition-cipher
; ------------------------------------------------------------------------------
; Given the dimensions of a grid, creates a function that can compute a
; horizontal route cipher.
;
; inputs: 
;   rows: The number of rows to use in the grid for the cipher
;   columns: The number of columns to use in the grid for the cipher
; outputs: 
;   A function that takes a plaintext message and returns a ciphertext message,
;   encoding using a horizontal-route grid of the specified dimensions.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(define (columnar-transposition-cipher rows columns)
  
  (lambda (string) (list->string(table->list(transpose (list->table (string->list string) rows columns))))))
;(lambda (string) (list->string(table->list(transpose (list->table (string->list (pad-message string (* rows columns))) rows columns))))))
(define (pad string len)
  (pad-message string len))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; random-char
; ------------------------------------------------------------------------------
; generates a random character
;
; Note: This function is provided to you as a utility, to help you implement
;       the pad function.
;
; inputs: 
;   none
; outputs: 
;   A random, lower-case character (a-z)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(define (random-char)
  (integer->char (random (char->integer #\a) (+ 1 (char->integer #\z)))))