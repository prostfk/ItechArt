import Article from "./Article";
import React from 'react'
export default function ArticlesList({articles}) {

    const articleElements = articles.map(article =>
        <Article key={article.id} article={article}/>
    );

    return (
        <ul style={{width:'100%'}}>{articleElements}</ul>
    )

}