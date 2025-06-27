import React from "react";
import useWishlist from "../../../../hooks/useWishlist.js";
import {
    Box,
    Card,
    CardContent,
    Typography,
    IconButton,
    Button,
    Divider,
    List,
    ListItem,
    ListItemText,
} from '@mui/material';
import {Delete} from '@mui/icons-material';

const Wishlist = () => {
    const {books} = useWishlist();

    return (
        <Box my={3} width={500} mx="auto">
            <Card>
                <CardContent>
                    <Typography variant="h5" gutterBottom>
                        Wishlist
                    </Typography>
                    <Divider sx={{mb: 2}}/>
                    <List>
                        {books.map(item => (
                            <ListItem
                                key={item.id}
                                secondaryAction={<IconButton edge="end" color="error"><Delete/></IconButton>}
                            >
                                <ListItemText
                                    primary={item.name}
                                    secondary={`${item.availableCopies}`}
                                />
                            </ListItem>
                        ))}
                    </List>
                    <Divider sx={{my: 2}}/>
                    {/*<Typography variant="h6">Total: ${getTotal()}</Typography>*/}
                    <Button variant="contained" color="primary" fullWidth sx={{mt: 2}}>
                        Checkout
                    </Button>
                </CardContent>
            </Card>
        </Box>
    );
};

export default Wishlist;