import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import GlobalService from "../../services/GlobalService";

const initialState = {
  loading: false,
  items: [],
  error: "",
};

// Generates pending, fulfilled and rejected action types
export const fetchItems = createAsyncThunk("user/fetchUsers", () => {
  let responseItem = GlobalService.getAllApiObjects();
  return responseItem;
});

const basketItemSlice = createSlice({
  name: "user",
  initialState,
  extraReducers: (builder) => {
    builder.addCase(fetchItems.pending, (state) => {
      state.loading = true;
    });
    builder.addCase(fetchItems.fulfilled, (state, action) => {
      state.loading = false;
      state.items = action.payload;
      state.error = "";
    });
    builder.addCase(fetchItems.rejected, (state, action) => {
      state.loading = false;
      state.items = [];
      state.error = action.error.message;
    });
  },
});

export default basketItemSlice.reducer;
