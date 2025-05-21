import React, {useState} from 'react';
import {Box, Button, CircularProgress} from "@mui/material";
import AccommodationsGrid from "../../components/accommodations/AccommodationsGrid/AccommodationsGrid.jsx";
import useAccommodations from "../../../hooks/useAccommodations.js";
import AddAccommodationDialog from "../../components/accommodations/AddAccommodationDialog/AddAccommodationDialog.jsx";
import CategoryToggle from "../../components/CategoryToggle/CategoryToggle";
import "./AccommodationsPage.css";

const AccommodationsPage = () => {
    const {accommodations, loading, onAdd, onEdit, onDelete} = useAccommodations();
    const [addAccommodationDialog, setAddAccommodationDialogOpen] = useState(false);
    const [selectedCategory, setSelectedCategory] = useState(null);

    const filteredAccommodations = selectedCategory
        ? accommodations.filter(acc => acc.category === selectedCategory)
        : accommodations;

    return (
        <>
            <Box className="accommodations-box">
                {loading && (
                    <Box className="progress-box">
                        <CircularProgress/>
                    </Box>
                )}

                {!loading && (
                    <>
                        <Box sx={{mb: 2}}>
                            <CategoryToggle
                                selectedCategory={selectedCategory}
                                setSelectedCategory={setSelectedCategory}
                            />
                        </Box>

                        <Box sx={{display: "flex", justifyContent: "flex-end", mb: 2}}>
                            <Button
                                variant="contained"
                                color="primary"
                                onClick={() => setAddAccommodationDialogOpen(true)}
                            >
                                Add Accommodation
                            </Button>
                        </Box>

                        <AccommodationsGrid
                            accommodations={filteredAccommodations}
                            onEdit={onEdit}
                            onDelete={onDelete}
                        />
                    </>
                )}
            </Box>

            <AddAccommodationDialog
                open={addAccommodationDialog}
                onClose={() => setAddAccommodationDialogOpen(false)}
                onAdd={onAdd}
            />
        </>
    );
};

export default AccommodationsPage;