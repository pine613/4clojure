(ns test_runner.core
  (:use [clojure.stacktrace]))

; テスト対象のソースファイル一覧を取得
(defn- src-files []
  (let [dirs (clojure.java.io/file "src/4clojure")
        files (file-seq dirs)]
    (filter #(->> % (.getName) (re-matches #"\d+.clj")) files)
  ))

; ソースファイルをテストする
(defn- test-file [file]
  (let [path (.getPath file)]
    (print "Testing " (.getName file) " ... ")
    
    (try
      (let [ret (load-file path)]
        (println (if ret " [Accepted]" " [Wrong Answer]"))
        ret
        )
      
      (catch Exception e
        (println " [Compile Error]")
        (println)
        (println (clojure.stacktrace/print-throwable e)))
      )
  ))

; ソースファイルの配列をテストする
(defn- test-files [files]
  (doseq [f files]
    (if-not (test-file f)
      ((println)
       (println "Test failed")
       (System/exit 1))))
  
  (println)
  (println "All file accepted")
  )

(defn -main []
  (test-files (src-files)))