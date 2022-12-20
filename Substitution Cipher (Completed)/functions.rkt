#lang racket

(provide alphabetic-only downcase list->table table->list transpose)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; alphabetic-only
; ------------------------------------------------------------------------------
; Strips a list of characters of any non-alphabetic characters
;
; inputs: 
;   chars: A list of characters
; outputs: 
;   The list of characters, filtered to contain only alphabetic characters.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(define (alphabetic-only chars)
  (filter char-alphabetic? chars))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;downcase
; ------------------------------------------------------------------------------
; Lowercases all charecters in a given list
;
; inputs: 
;   chars: A list of characters
; outputs: 
;   The list of characters, filtered to contain only be lowercase
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(define (downcase L)
  (map char-downcase L))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; list->table
; ------------------------------------------------------------------------------
; Given a list of elements, a number of rows r, and a number of columns c,
; returns a list of r lists where each list has c values.
;
; Assumption: the length of the list is r * c.
;
; inputs: 
;   elements: a list of values
;   rows: the number of rows in the table
;   columns: the number of columns in the table
;
; outputs: 
;   A table with the specified number of rows and columns, where the data is
;   in row-major order (i.e., the first c elements of the data become the first
;   row of the table, where c is the number of columns).
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(define (list->table elements rows columns)
  (if (empty? elements)
      empty
      (cons (take elements columns)
            (list->table (drop elements columns) (- rows 1) columns))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; table->list
; ------------------------------------------------------------------------------
; Takes a table and converst it to a list
;
; inputs: 
;   chars: A table of elements
; outputs: 
;   A list of elements in the table in row-major order
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(define (table->list table)
   (foldr append empty table))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; transpose
; ------------------------------------------------------------------------------
; Takes a table with r rows and c columns, and returns a table with c rows and r columns.
;
; inputs: 
;   2-D Table
; outputs: 
;   A table with the same values that how the amount of columns and rows flipped
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(define (transpose table)
(define (colmaker L)
     (if (empty? L)
         empty
         (cons (first (first L)) (colmaker (rest L)))))
  (define(remov L)
    (if (empty? L)
         empty
    (drop L 1)))

  
  (cond [(empty? table) '()]
    [(empty? (first table)) (list (table->list table))]    
    [(empty? (rest(first table))) (list (table->list table))]
    [else (cons (colmaker table) (transpose(map remov table)))]))
      

         
