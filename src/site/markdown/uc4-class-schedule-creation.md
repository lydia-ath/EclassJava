# ΠΧ4.  Δημιουργία Ωρολογίου Προγράμματος

**Πρωτεύων Actor**: Γραμματεία 
**Ενδιαφερόμενοι**
**Γραμματεία**: Θέλει να μπορεί να δημιουργεί το ωρολόγιο πρόγραμμα κάθε τμήματος του σχολείου.
**Καθηγητής**: Θέλει να γνωρίζει ποιες μέρες και ποιες ώρες της εβδομάδας διδάσκει κάθε μάθημα και σε ποιο τμήμα.  
**Μαθητής**: Θέλει να γνωρίζει ποιες μέρες και ποιες ώρες της εβδομάδας παρακολουθεί κάθε μάθημα.
**Προϋποθέσεις**: Να υπάρχουν εγγεγραμμένοι μαθητές σε κάθε τάξη του σχολείου και να υπάρχει 
ο διαθέσιμος αριθμός εκπαιδευτικών στη σχολική μονάδα. 

## Βασική Ροή

## Α) Δημιουργία Ωρολογίου Προγράμματος Τμήματος

1.	Η Γραμματεία επιλέγει την τάξη που επιθυμεί από τη λίστα που περίεχει όλες τις τάξεις του Γυμνασίου (Α,Β & Γ Γυμνασίου).\
	1α. Η Γραμματεία ακυρώνει την επιλογή της συγκεκριμένης τάξης.\
		i. Η Περίπτωση Χρήσης τερματίζει.
2.	Το σύστημα εμφανίζει την επιλεγμένη τάξη μαζί με τη λίστα των τμημάτων της συγκεκριμένης τάξης. 
3.	Η Γραμματεία επιλέγει το επιθυμητό τμήμα από τη λίστα των τμημάτων της τάξης.\
	3α. Η Γραμματεία ακυρώνει την επιλογή του συγκεκριμένου τμήματος.\
		i.Η Περίπτωση Χρήσης επιστρέφει στο βήμα 3 της βασικής ροής.
4.	Το σύστημα εμφανίζει μια λίστα με όλα τα μαθήματα που αντιστοιχούν στην συγκεκριμένη τάξη.
5.	Η Γραμματεία επιλέγει το μάθημα που επιθυμεί από τη λίστα των αντίστοιχων μαθημάτων.\
	5α. Η Γραμματεία ακυρώνει την επιλογή του συγκεκριμένου μαθήματος.\ 
		i.Η Περίπτωση Χρήσης επιστρέφει στο βήμα 4 της βασικής ροής.
6.	Το σύστημα εμφανίζει τις προκαθορισμένες ώρες διδασκαλίας του μαθήματος.
7.  Το σύστημα παρουσιάζει μια λίστα με τους καθηγητές που διδάσκουν το συγκεκριμένο μάθημα.
8.  Η Γραμματεία επιλέγει έναν καθηγητή από τη λίστα των καθηγητών.\
	8α. Ο καθηγητής έχει συμπληρώσει όλες τις διαθέσιμες ώρες διδασκαλίας.\
		i. Το σύστημα εμφανίζει κατάλληλο μήνυμα σφάλματος.\
		ii. Η Περίπτωση Χρήσης επιστρέφει στο βήμα 7.
9.  Το σύστημα εμφανίζει μια λίστα με τις ημέρες της εβδομάδας.
10. Η Γραμματεία επιλέγει την ημέρα που θα πραγματοποιείται το μάθημα.
11. Το σύστημα εμφανίζει μια λίστα με τις ώρες της ημέρας.
12. Η Γραμματεία επιλέγει την ώρα διεξαγωγής του μαθήματος.\
	12α. Η ώρα που επιλέχθηκε αλληλοκαλύπτεται από άλλο μάθημα του ωρολογίου προγράμματος.\
		i. Το σύστημα εμφανίζει κατάλληλο μήνυμα σφάλματος.\
		ii. Η Περίπτωση Χρήσης επιστρέφει στο βήμα 11 της βασικής ροής.
13. Το σύστημα ελέγχει την εγκυρότητα των δεδομένων.\
	13α. Οι ώρες διδασκαλίας της ημέρας έχουν συμπληρωθεί.\
		i. Το σύστημα εμφανίζει κατάλληλο μήνυμα σφάλματος.\
		ii. Η Περίπτωση Χρήσης τερματίζει.\
	13β. Οι ώρες του μαθήματος έχουν συμπληρωθεί.\
		i. Το σύστημα εμφανίζει κατάλληλο μήνυμα σφάλματος.\
		ii. Η Περίπτωση Χρήσης επιστρέφει στο βήμα 5.
14. Το σύστημα καταχωρεί στο τμήμα το μάθημα, τον καθηγητή καθώς και την αντίστοιχη ημέρα και ώρα της σχολικής ημέρας όπου θα διεξάγεται το μάθημα.\
	14α. Οι ώρες του μαθήματος δεν έχουν συμπληρωθεί.\
		i. Το σύστημα εμφανίζει κατάλληλο ενημερωτικό μήνυμα.\
		ii. Η Περίπτωση Χρήσης επιστρέφει στο βήμα 9.
15. Η ροή επιστρέφει στο βήμα 4 της βασικής ροής εως ότου τοποθετηθούν όλα τα μαθήματα στο ωρολόγιο πρόγραμμα.\
	15α. Δεν έχουν εισαχθεί όλα τα μαθήματα της τάξης στο ωρολόγιο πρόγραμμα.\ 
		i. Το σύστημα εμφανίζει κατάλληλο ενημερωτικό μήνυμα.\
		ii. Η Περίπτωση Χρήσης επιστρέφει στο βήμα 4.
16. Η διαδικασία κατάρτησης του ωρολογίου προγράμματος για το συγκεκριμένο τμήμα ολοκληρώθηκε.


## Β) Τροποποίηση Ωρολογίου Προγράμματος

1.	Η Γραμματεία επιλέγει την τάξη που επιθυμεί από τη λίστα που περίεχει όλες τις τάξεις του Γυμνασίου (Α,Β & Γ Γυμνασίου).
2.	Το σύστημα εμφανίζει την επιλεγμένη τάξη μαζί με τη λίστα των τμημάτων της συγκεκριμένης τάξης. 
3.	Η Γραμματεία επιλέγει το επιθυμητό τμήμα από τη λίστα των τμημάτων της τάξης.
4.	Το σύστημα εμφανίζει μια λίστα με όλα τα μαθήματα που αντιστοιχούν στην συγκεκριμένη τάξη.
5.	Η Γραμματεία επιλέγει το μάθημα που επιθυμεί από τη λίστα των αντίστοιχων μαθημάτων.
6.  Το σύστημα παρουσιάζει μια λίστα με τους καθηγητές που διδάσκουν το συγκεκριμένο μάθημα.\
	6α. Η Γραμματεία επιθυμεί να τροποποιήσει την ημέρα ή/και ώρα του μαθήματος.\
		i. Η Περίπτωση Χρήσης πηγαίνει στο βήμα 10.
7.  Η Γραμματεία επιλέγει τον καθηγητή που επιθυμεί να του αναθέσει το μάθημα από τη λίστα των καθηγητών.\
	7α. Ο καθηγητής έχει συμπληρώσει όλες τις διαθέσιμες ώρες διδασκαλίας.\
		i. Το σύστημα εμφανίζει κατάλληλο μήνυμα σφάλματος.\
		ii. Η Περίπτωση Χρήσης επιστρέφει στο βήμα 6.
8.  Το σύστημα καταχωρεί το μάθημα στον καινούριο καθηγητή.
9.  Το σύστημα ενημερώνει τις αντίστοιχες ώρες των καθηγητών.
10. Το σύστημα παρουσιάζει μια λίστα με τις ημέρες της εβδομάδας.\
	10α. Η Γραμματεία ολοκληρώνει την τροποποίηση του ωρολογίου προγράμματος.\
		i. Η Περίπτωση Χρήσης τερματίζει.
11. Η Γραμματεία επιλέγει την ημέρα όπου θα γίνεται το μάθημα.\
	11α. Οι ώρες διδασκαλίας της ημέρας έχουν συμπληρωθεί.\
		i. Το σύστημα εμφανίζει κατάλληλο μήνυμα σφάλματος.\
		ii. Η Περίπτωση Χρήσης επιστρέφει στο βήμα 10.
12. Το σύστημα εμφανίζει μια λίστα με τις διαθέσιμες ώρες διδασκαλίας του τμήματος για την συγκεκριμένη ημέρα.
13. Η Γραμματεία επιλέγει την ώρα διεξαγωγής του μαθήματος.\
	13α. Η συγκεκριμένη ώρα δεν είναι διαθέσιμη.\
		i. Το σύστημα εμφανίζει κατάλληλο μήνυμα σφάλματος.\
		ii. Η Περίπτωση Χρήσης επιστρέφει στο βήμα 12.
14. Το σύστημα καταχωρεί το μάθημα στην επιλεγμένη ημέρα και ώρα.
15. Η Περίπτωση Χρήσης τερματίζει.


## Διαγράμματα

\[*Εισάγουμε διαγράμματα δραστηριοτήτων με τα βήματα επιλεγμένων σεναρίων χρήσης.*\]