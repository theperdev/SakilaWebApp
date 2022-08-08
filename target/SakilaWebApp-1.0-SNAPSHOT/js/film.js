function confirmDelete(urlActionDelete) {
    if (confirm("Are you sure you want to delete this film?")) {
        location.href = urlActionDelete;
    }
}