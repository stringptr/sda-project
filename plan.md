
### Greedy job scheduling

## Definition
  Setiap job dengan duration(waktu pengerjaan yang dibutuhkan), deadlines dan reward/penalti dari menyelesaikan atau tidak menyelesaikan job
  Goal = mencari job yang harus dikerjakan atau dilewati untuk mendapat poin tertinggi

## Variable
  # Mandatory =
    1. Duration
    2. Deadline
    3. Reward of completion
    4. Penalty of non-completion
  # Optional =
    1. Dependency = Satu job menjadi pre-requisite untuk mengerjakan job lain
    2. Resource Limitation = Resource dibutuhkan untuk menyelesaikan suatu job
    3. Pre-Emptive = job bisa di-pause dan dilanjutkan selama belum mencapai deadline, mungkin penalti jika proses di-delay
    4. MultiProcessing = job bisa berjalan secara paralel

## Algorithm Used
  1. Interval Sorting
  2. Topological Graph
  3. DAG, A Directed Acyclic Graph
