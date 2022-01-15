# ΠΧ5.  Έκδοση Αναφοράς

**Πρωτεύων Actor**: Γραμματεία  
**Ενδιαφερόμενοι**
**Γραμματεία**: Θέλει να μπορεί να εκτελεί την έκδοση συνολικής αναφοράς με την βαθμολογία και τυχόν σχόλια για κάθε μάθημα σε κάθε μαθητή και για όλα τα μαθήματά του μαζί.  
**Μαθητής**: Θέλει να μπορεί να βλέπει την συνολική αναφορά της προόδου του.
**Προϋποθέσεις**: Ο Καθηγητής έχει εκτελέσει με επιτυχία την Περίπτωση Χρήσης “Αξιολόγηση Μαθητή”.  

## Βασική Ροή

1.	Το σύστημα εμφανίζει μια λίστα με τα διαθέσιμα τμήματα του γυμνασίου (Α1 γυμνασίου, Α2 γυμνασίου κ.α.).
2.	Η Γραμματεία επιλέγει ένα από τα διαθέσιμα τμήματα.\
	2α. Η Γραμματεία ακυρώνει την επιλογή του συγκεκριμένου τμήματος.\
		i. Η Περίπτωση Χρήσης τερματίζει.\
	2β. Η Γραμματεία διορθώνει την επιλογή του συγκεκριμένου τμήματος.\
		i. Η Περίπτωση Χρήσης επιστρέφει στο βήμα 1 της βασικής ροής.
3.	Το σύστημα εμφανίζει λίστα με τους εγγεγραμμένους μαθητές του συγκεκριμένου τμήματος.
4.	Η Γραμματεία επιλέγει τον μαθητή του οποίου την αναφορά θέλει να εκδόσει.\
	4α. Η Γραμματεία ακυρώνει την επιλογή του συγκεκριμένου μαθητή.\
		i. Η Περίπτωση Χρήσης τερματίζει.\
	4β. Η Γραμματεία διορθώνει την επιλογή του συγκεκριμένου μαθητή.\
		i. Η Περίπτωση Χρήστης επιστρέφει στο βήμα 3 της βασικής ροής.\
5.	Το σύστημα εμφανίζει την καρτέλα με τα στοιχεία του μαθητή (αριθμός μητρώου, ονοματεπώνυμο, τάξη, τμήμα).
6.	Η Γραμματεία εκτελεί την επιλογή για την έκδοση της συνολικής αναφοράς βαθμολογίας και σχόλια για το μαθητή.\
	6α. Η Γραμματεία ακυρώνει την επιλογή έκδοσης της συνολικής αναφοράς βαθμολογίας και σχολίων για το μαθητή.\
		i. Η Περίπτωση Χρήσης τερματίζει.
7.	Το σύστημα ελέγχει αν υπάρχει η αντίστοιχη βαθμολογία και τα σχόλια για κάθε μάθημα που παρακολούθησε ο συγκεκριμένος μαθητής.\
	7α. Το σύστημα αναγνωρίζει ότι δεν έχει καταχωρηθεί η βαθμολογία ή/και τα σχόλια ενός ή περισσότερων μαθημάτων του μαθητή.\
		i.	Το σύστημα ενημερώνει την γραμματεία και τον Καθηγητή με κατάλληλο μήνυμα σφάλματος.\
		ii.	Η έκδοση συνολικής αναφοράς τερματίζει.	
8.	Το σύστημα δημιουργεί την συνολική αναφορά για το μαθητή.
9.	Η Γραμματεία πραγματοποιεί την έκδοση της συνολικής αναφοράς.

   
## Διαγράμματα

\[*Εισάγουμε διαγράμματα δραστηριοτήτων με τα βήματα επιλεγμένων σεναρίων χρήσης.*\]