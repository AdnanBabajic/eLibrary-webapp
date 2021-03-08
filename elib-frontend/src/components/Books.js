import React, { useState, useEffect } from "react";
import "./Books.css";
import { makeStyles } from "@material-ui/core/styles";
import Card from "@material-ui/core/Card";
import CardActionArea from "@material-ui/core/CardActionArea";
import CardActions from "@material-ui/core/CardActions";
import CardContent from "@material-ui/core/CardContent";
import CardMedia from "@material-ui/core/CardMedia";
import Button from "@material-ui/core/Button";
import Typography from "@material-ui/core/Typography";
import LearnMoreDialog from "./LearnMoreDialog";

export const Books = () => {
  const [books, setBooks] = useState([]);
  const [selectedBook, setSelectedBook]=useState(null)
  const [isDialogOpen, setIsDialogOpen]=useState(false)

  useEffect(()=>{    
  const getBooks = async () => {
    const res = await fetch("http://localhost:8080/elibrary/book", {
      method: "GET",
      headers: {
        "Content-type": "application/json",
        Accept: "application/json",
        "Access-Control-Allow-Headers": "*",
        "Access-Control-Allow-Credentials": "true",
        "Access-Control-Allow-Methods": "GET, HEAD, OPTIONS, POST, PUT",
        "Access-Control-Allow-Origin": "http://localhost:3000/",
      },
    });
    if (res.ok) {
      const data = await res.json();
      setBooks(data);
    }
  }
  getBooks();
  },[])

  const openLMDialog =  (index) => (event) => {
    setSelectedBook(index)
    setIsDialogOpen(true)
  }
  
  return (
    <div>
       {books.map((book, index) => (
   <Card className={book.root}>
   <CardActionArea>
     <CardMedia
       component="img"
       alt="Book cover"
       height="140"
       image={book.coverUrl}
       title="Book cover"
     />
     <CardContent>
       <Typography gutterBottom variant="h5" component="h2">
         {book.name}
       </Typography>
       <Typography variant="body2" color="textSecondary" component="p">
         {book.writer}
       </Typography>
     </CardContent>
   </CardActionArea>
   <CardActions>
     <Button size="small" color="primary" onClick={openLMDialog(index)}>
       Learn more
     </Button>
     <Button size="small" color="primary">
       Pickup
     </Button>
   </CardActions>
 </Card>
      ))}

      {selectedBook && <LearnMoreDialog books={books} open={isDialogOpen} selectedBook={selectedBook} />}
    </div>
  );
};
