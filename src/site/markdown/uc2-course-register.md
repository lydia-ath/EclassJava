## ΠΧ2. ΚΑΤΑΧΩΡΗΣΗ ΜΑΘΗΜΑΤΩΝ ΑΝΑ ΤΑΞΗ

**Πρωτεύων Actor**: Γραμματεία
**Ενδιαφερόμενοι**
**Γραμματεία**: Πρέπει να έχει την δυνατότητα καταχώρησης μαθημάτων ανά τάξη.
**Προϋποθέσεις**: Το Σύστημα να εμφανίζει την λίστα με τις διαθέσιμες τάξεις και την λίστα με τα διαθέσιμα μαθήματα του γυμνασίου.

## Βασική Ροή

## Α) Εισαγωγή Μαθημάτων σε κάθε Τάξη 

1. Το σύστημα εμφανίζει μια λίστα με τις διαθέσιμες τάξεις του γυμνασίου(Α, Β και Γ γυμνασίου αντίστοιχα).
2. Η Γραμματεία επιλέγει μια από τις διαθέσιμες τάξεις.\
	2α. Η Γραμματεία ακυρώνει την επιλογή της συγκεκριμένης τάξης.\
		i. Η Περίπτωση Χρήσης τερματίζει.\
	2β. Η Γραμματεία διορθώνει την επιλογή της συγκεκριμένης τάξης.\
		i. Η Περίπτωση Χρήσης επιστρέφει στο βήμα 1 της βασικής ροής.
3. Το σύστημα εμφανίζει λίστα με όλα τα μαθήματα γυμνασίου που μπορούν να καταχωρηθούν στις τάξεις του γυμνασίου.
4. Η Γραμματεία επιλέγει τα μαθήματα που θα αντιστοιχιθούν στη συγκεκριμένη τάξη.\
	4α. Η Γραμματεία ακυρώνει την επιλογή ανάθεσης των συγκεκριμένων μαθημάτων στη συγκεκριμένη τάξη.\
		i. Η Περίπτωση Χρήσης τερματίζει.
5. Η ροή επιστρέφει στο βήμα 1. 
6. Η διαδικασία καταχώρησης μαθημάτων ανά τάξη τερματίζει όταν έχουν καταχωρηθεί μαθήματα σε όλες τις τάξεις. 


## Β) Ενημέρωση Μαθημάτων σε Τάξη 

1. Το σύστημα εμφανίζει μια λίστα με τις διαθέσιμες τάξεις του γυμνασίου(Α, Β και Γ γυμνασίου αντίστοιχα).
2. Η Γραμματεία επιλέγει μια από τις διαθέσιμες τάξεις.\
	2α. Η Γραμματεία ακυρώνει την επιλογή της συγκεκριμένης τάξης.\
		i. Η Περίπτωση Χρήσης τερματίζει.\
	2β. Η Γραμματεία διορθώνει την επιλογή της συγκεκριμένης τάξης.\
		i. Η Περίπτωση Χρήσης επιστρέφει στο βήμα 1 της βασικής ροής.
3. Το σύστημα εμφανίζει λίστα με τα μαθήματα γυμνασίου που έχουν καταχωρηθεί στη συγκεκριμένη τάξη.
4. Η Γραμματεία επιλέγει τα μαθήματα που χρειάζεται να ενημερωθούν.\
	4α. Η Γραμματεία ακυρώνει την επιλογή των μαθημάτων που θα ενημερωθούν.\
		i. Η Περίπτωση Χρήσης τερματίζει.\
	4β. Η Γραμματεία διορθώνει την επιλογή των μαθημάτων που θα ενημερωθούν.\
		i. Η Περίπτωση Χρήσης επιστρέφει στο βήμα 3 της βασικής ροής.
5. Το σύστημα εμφανίζει λίστα με όλα τα μαθήματα γυμνασίου που μπορούν να καταχωρηθούν στις τάξεις του γυμνασίου.
6. Η Γραμματεία επιλέγει τα μαθήματα που θα αντιστοιχιθούν στη συγκεκριμένη τάξη.\
	6α. Η Γραμματεία ακυρώνει τα νέα μαθήματα που θα αποδοθούν στη συγκεκριμένη τάξη.\
		i. Η Περίπτωση Χρήσης τερματίζει.\
	6β. Η Γραμματεία διορθώνει την επιλογή των νέων μαθημάτων που θα αποδοθούν στη συγκεκριμένη τάξη.\
		i. Η Περίπτωση Χρήσης επιστρέφει στο βήμα 5 της βασικής ροής.
7. Η διαδικασία ενημέρωσης μαθημάτων της τάξης τερματίζει.  

## Διαγράμματα

\[*Εισάγουμε διαγράμματα δραστηριοτήτων με τα βήματα επιλεγμένων σεναρίων χρήσης.*\]