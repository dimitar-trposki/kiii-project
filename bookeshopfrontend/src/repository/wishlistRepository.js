import axiosInstance from "../axios/axios.js";

const wishlistRepository = {
    getActiveWishlist: async () => {
        return await axiosInstance.get("/wishlist");
    },
    addBookToWishlist: async (id) => {
        return await axiosInstance.post(`/wishlist/add-product/${id}`);
    },
};

export default wishlistRepository;