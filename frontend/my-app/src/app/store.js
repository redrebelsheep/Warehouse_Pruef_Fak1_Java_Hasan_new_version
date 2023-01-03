import { configureStore } from "@reduxjs/toolkit";
import basketItemReducer from "../features/basketItem/basketItemSlice";

const store = configureStore({
  reducer: {
    items: basketItemReducer,
  },
});

export default store;
