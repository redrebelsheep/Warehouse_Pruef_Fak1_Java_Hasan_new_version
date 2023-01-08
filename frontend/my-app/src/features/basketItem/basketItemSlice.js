import { createSlice, createAsyncThunk, current } from "@reduxjs/toolkit";
import GlobalService from "../../services/GlobalService";

const initialState = {
  loading: false,
  items: [],
  error: "",
};

// Generates pending, fulfilled and rejected action types
export const fetchItems = createAsyncThunk("item/fetchItems", () => {
  let responseItem = GlobalService.getAllApiObjects();
  return responseItem;
});

export const postItem = createAsyncThunk("item/postItem", (object) => {
  let responseItem = GlobalService.postApiObject(object);
  return responseItem;
});

const basketItemSlice = createSlice({
  name: "item",
  initialState,
  reducers: {
    chaneItemName: (state, action) => {
      state.items.pop();
    },
    outputState: (state, action) => {
      console.log(action);
      console.log(state);
      console.log(current(state));
    },
  },
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
    builder.addCase(postItem.pending, (state) => {
      state.loading = true;
    });
    builder.addCase(postItem.fulfilled, (state, action) => {
      state.loading = false;
      state.items = state.items.push(action.payload);
      state.error = "";
    });
    builder.addCase(postItem.rejected, (state, action) => {
      state.loading = false;
      state.items = [...state.items];
      state.error = action.error.message;
    });
  },
});

export default basketItemSlice.reducer;
export const { chaneItemName, outputState } = basketItemSlice.actions;
