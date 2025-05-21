import React from 'react';
import ToggleButton from '@mui/material/ToggleButton';
import ToggleButtonGroup from '@mui/material/ToggleButtonGroup';

const categories = [
    'ALL',
    'ROOM',
    'HOUSE',
    'FLAT',
    'APARTMENT',
    'HOTEL',
    'MOTEL'
];

const CategoryToggle = ({selectedCategory, setSelectedCategory}) => {
    const handleCategoryChange = (event, newCategory) => {
        if (newCategory !== null) {
            setSelectedCategory(newCategory === 'ALL' ? null : newCategory);
        }
    };

    return (
        <ToggleButtonGroup
            value={selectedCategory || 'ALL'}
            exclusive
            onChange={handleCategoryChange}
            aria-label="category selection"
            fullWidth
        >
            {categories.map((category) => (
                <ToggleButton key={category} value={category} aria-label={category.toLowerCase()}>
                    {category}
                </ToggleButton>
            ))}
        </ToggleButtonGroup>
    );
};

export default CategoryToggle;