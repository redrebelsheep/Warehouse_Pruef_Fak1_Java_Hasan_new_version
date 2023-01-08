import { Box, Typography, useTheme } from "@mui/material";
import { DataGrid } from "@mui/x-data-grid";
import { tokens } from "../../Theme/Theme";
import { mockDataInvoices, mockDataItems } from "../../data/mockData";
import Button from "@mui/material/Button";
import Header from "../basic/Header";
import DeleteForeverIcon from "@mui/icons-material/DeleteForever";
import { useSelector } from "react-redux";

const BasketItemComponent = () => {
  const items = useSelector((state) => state.items.items);
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);

  const rows = items.map((item) => ({
    id: item.id,
    productName: item.productName,
    purchasingPrice: item.purchasingPrice,
  }));

  const output = () => {
    console.log(items);
    items.map((e) => console.log(e));
  };

  const columns = [
    { field: "id", headerName: "ID" },
    {
      field: "productName",
      headerName: "productName",
      flex: 1,
      cellClassName: "name-column--cell",
    },
    {
      field: "purchasingPrice",
      headerName: "purchasingPrice",
      flex: 1,
      renderCell: (params) => (
        <Typography color={colors.greenAccent[500]}>
          ${params.row.purchasingPrice}
        </Typography>
      ),
    },
    {
      field: "delete",
      headerName: "Delete",
      width: 150,
      headerAlign: "center",
      align: "center",
      renderCell: (cellValues) => (
        <strong>
          <Button
            type="button"
            fullWidth
            variant="contained"
            sx={{ marginLeft: 1, mt: 3, mb: 2, bgcolor: "primary.main" }}
          >
            <DeleteForeverIcon />
          </Button>
        </strong>
      ),
    },
  ];

  return (
    <Box m="20px">
      {console.log(items)};
      <Header title="INVOICES" subtitle="List of Invoice Balances" />
      <Box
        m="40px 0 0 0"
        height="75vh"
        sx={{
          "& .MuiDataGrid-root": {
            border: "none",
          },
          "& .MuiDataGrid-cell": {
            borderBottom: "none",
          },
          "& .name-column--cell": {
            color: colors.greenAccent[300],
          },
          "& .MuiDataGrid-columnHeaders": {
            backgroundColor: colors.blueAccent[700],
            borderBottom: "none",
          },
          "& .MuiDataGrid-virtualScroller": {
            backgroundColor: colors.primary[400],
          },
          "& .MuiDataGrid-footerContainer": {
            borderTop: "none",
            backgroundColor: colors.blueAccent[700],
          },
          "& .MuiCheckbox-root": {
            color: `${colors.greenAccent[200]} !important`,
          },
        }}
      >
        <DataGrid checkboxSelection rows={rows} columns={columns} />
      </Box>
      <Button onClick={output}>output</Button>
    </Box>
  );
};

export default BasketItemComponent;
