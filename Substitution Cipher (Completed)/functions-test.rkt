#lang racket

(require rackunit)
(require "functions.rkt")

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; alphabetic-only tests
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(check-equal? (alphabetic-only '()) '())
(check-equal? (alphabetic-only '(#\a #\b #\C #\d #\e)) '(#\a #\b #\C #\d #\e))
(check-equal? (alphabetic-only '(#\1 #\2 #\3)) '())
(check-equal? (alphabetic-only '(#\1 #\2 #\3 #\space #\s)) '(#\s))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; downcase tests
(check-equal? (downcase '(#\A)) '(#\a))
(check-equal? (downcase '(#\B)) '(#\b))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; TODO: Add at least two more cases
(check-equal? (downcase '(#\a #\b #\c #\d #\e)) '(#\a #\b #\c #\d #\e))
(check-equal? (downcase '(#\a #\b #\C #\d #\e)) '(#\a #\b #\c #\d #\e))
(check-equal? (downcase '(#\A #\C #\B)) '(#\a #\c #\b))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; list->table tests
(check-equal? (list->table '(1 2 5 6) 1 2) '((1 2) (5 6)))

(check-equal? (list->table '(1 2 3 4) 1 4) '((1 2 3 4)))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; TODO: Add at least two more cases
(check-equal? (list->table '(1 2 3) 1 3) '((1 2 3)))
(check-equal? (list->table '(1 2 3) 3 1) '((1) (2) (3)))
(check-equal? (list->table '(1 2 3 4 5 6) 3 2) '((1 2) (3 4) (5 6)))
(check-equal? (list->table '(1 2 3 4 5 6) 2 3) '((1 2 3) (4 5 6)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; table->list tests
(check-equal? (table->list '((1 2) (3 4) (5 6))) '(1 2 3 4 5 6))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; TODO: Add at least two test cases
(check-equal? (table->list '((1 2 3) (3 4 3) (5 6 3))) '(1 2 3 3 4 3 5 6 3))
(check-equal? (table->list '((1 2) (3 4))) '(1 2 3 4))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; transpose tests
(check-equal? (transpose '((1 2) (3 4) (5 6))) '((1 3 5) (2 4 6)))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; TODO: Add at least two test cases
(check-equal? (transpose '((1 2) (3 4) (5 6) (7 8))) '((1 3 5 7) (2 4 6 8)))
(check-equal? (transpose '((1) (2) (3) (4))) '((1 2 3 4)))
