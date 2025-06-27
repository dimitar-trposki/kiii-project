import {useEffect, useState} from "react";
import wishlistRepository from "../repository/wishlistRepository.js";

const useWishlist = () => {
    const [wishlist, setWishlist] = useState({
        "id": null,
        "dateCreated": null,
        "user": null,
        "books": [],
        "wishlistStatus": null,
    });

    useEffect(() => {
        wishlistRepository
            .getActiveWishlist()
            .then((response) => {
                setWishlist(response.data);
            })
            .catch((error) => console.log(error));
    }, []);

    return wishlist;
};

export default useWishlist;