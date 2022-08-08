/*
    The usp_UpdateFilm is used for both INSERT and UPDATE actions.
    When a film id greater than zero is passed the procedure performs an UPDATE,
    otherwise it performs an INSERT.
*/


DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_UpdateFilm` (
	fId int,
    fTitle varchar(255),
    fDescription text,
    fReleaseYear year,
    fLanguageID tinyint,
    fOriginalLanguageID tinyint,
    fRentalDuration tinyint,
    fRentalRate decimal(4,2),
    fLength smallint,
    fReplacementCost decimal(4,2),
    fRating varchar(10),
    fSpecialFeatures varchar(100),
    fLastUpdate timestamp
)
BEGIN
	IF (fId = 0) THEN
		INSERT INTO film (title, description, release_year, language_id, original_language_id,
        rental_duration, rental_rate, length, replacement_cost, rating, special_features, last_update)
        VALUES (fTitle, fDescription, fReleaseYear, fLanguageID, fOriginalLanguageID,
        fRentalDuration, fRentalRate, fLength, fReplacementCost, fRating, fSpecialFeatures, fLastUpdate);
ELSE
UPDATE film
SET title = fTitle,
    description = fDescription,
    release_year = fReleaseYear,
    language_id = fLanguageID,
    original_language_id = fOriginalLanguageID,
    rental_duration = fRentalDuration,
    rental_rate = fRentalRate,
    length = fLength,
    replacement_cost = fReplacementCost,
    rating = fRating,
    special_features = fSpecialFeatures,
    last_update = fLastUpdate
WHERE film_id = fId;
END IF;
END$$
DELIMITER ;


/*
    The usp_DeleteFilm is used to delete a film.
    Because the film deletion involves deleting rows from related tables
    all the actions have been enclosed in a transaction. Any failure will rollback
    all the previous actions.
*/

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_DeleteFilm`
(
	fId int
)
BEGIN
	DECLARE `_rollback` BOOL DEFAULT 0;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET `_rollback` = 1;

    START TRANSACTION;

    delete from film_actor where film_id = fId;
    delete from film_category where film_id = fId;
    delete from rental where inventory_id IN (select inventory_id from inventory where film_id = fId);
    delete from inventory where film_id = fId;
    delete from film where film_id = fId;

    IF `_rollback` THEN
        ROLLBACK;
    ELSE
        COMMIT;
    END IF;
END$$
DELIMITER ;

