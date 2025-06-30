import {useCallback, useEffect, useState} from "react";
import authorRepository from "../repository/authorRepository.js";

const initialState = {
    "authors": [],
    "loading": true,
};

const useAuthors = () => {
    const [state, setState] = useState(initialState);

    const fetchAuthors = useCallback(() => {
        setState(initialState);
        authorRepository
            .findAll()
            .then((response) => {
                setState({
                    "authors": response.data,
                    "loading": false,
                });
            })
            .catch((error) => console.log(error));
    }, []);

    const onAdd = useCallback((data) => {
        authorRepository
            .add(data)
            .then(() => {
                console.log("Successfully added a new author.");
                fetchAuthors();
            })
            .catch((error) => console.log(error));
    }, [fetchAuthors]);

    const onEdit = useCallback((id, data) => {
        authorRepository
            .edit(id, data)
            .then(() => {
                console.log(`Successfully edited the product with ID ${id}.`);
                fetchAuthors();
            })
            .catch((error) => console.log(error));
    }, [fetchAuthors]);

    const onDelete = useCallback((id) => {
        authorRepository
            .delete(id)
            .then(() => {
                console.log(`Successfully deleted the product with ID ${id}.`);
                fetchAuthors();
            })
            .catch((error) => console.log(error));
    }, [fetchAuthors]);

    useEffect(() => {
        fetchAuthors();
    }, [fetchAuthors]);

    return {
        // ...state,
        authors: state.authors,
        loading: state.loading,
        onAdd: onAdd,
        onEdit: onEdit,
        onDelete: onDelete
    };
};

export default useAuthors;