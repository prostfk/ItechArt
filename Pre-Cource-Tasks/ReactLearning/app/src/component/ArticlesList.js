import Article from "./Article";
import React from 'react'
export default function ArticlesList({articles}) {

    const articleElements = articles.map((article, index) =>
        <Article key={article.id} article={article} defaultOpen = {index === 0}/>
    );

    return (
        <ul style={{width:'100%'}}>{articleElements}</ul>
    )

}