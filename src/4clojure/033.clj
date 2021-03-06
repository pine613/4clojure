(defn- f [s n]
  (->> s
       (map #(repeat n %))
       (apply concat)))

(=
  (= (f [1 2 3] 2) '(1 1 2 2 3 3))
  (= (f [:a :b] 4) '(:a :a :a :a :b :b :b :b))
  (= (f [4 5 6] 1) '(4 5 6))
  (= (f [[1 2] [3 4]] 2) '([1 2] [1 2] [3 4] [3 4]))
  (= (f [44 33] 2) [44 44 33 33])
  true)