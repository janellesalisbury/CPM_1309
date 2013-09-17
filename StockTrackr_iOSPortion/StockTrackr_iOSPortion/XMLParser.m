//
//  XMLParser.m
//  StockTrackr_iOSPortion
//
//  Created by Janelle Salisbury on 9/15/13.
//  Copyright (c) 2013 Janelle Salisbury. All rights reserved.
//

#import "XMLParser.h"


@implementation XMLParser

-(id) initXMLParser{
    if (self == [super init]) {
        app = (AppDelegate *)[[UIApplication sharedApplication] delegate];
    }
    return self;
}

-(void)parser:(NSXMLParser *)parser didStartElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName attributes:(NSDictionary *)attributeDict{
    
    if ([elementName isEqualToString:@"stocks"]) {
        app.stockArray = [[NSMutableArray alloc] init];
    }
    else if ([elementName isEqualToString:@"stock"]){
        stockList = [[Stock alloc] init];
        
        stockList.name = [[attributeDict objectForKey:@"name"] stringValue];
    }
    
}

-(void)parser:(NSXMLParser *)parser foundCharacters:(NSString *)string{
    
    if(!xmlValue){
        xmlValue = [[NSMutableString alloc] initWithString:string];
        
    }
    else
        [xmlValue appendString:string];
    
}

-(void)parser:(NSXMLParser *)parser didEndElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName{
    
    if([elementName isEqualToString:@"stocks"]){
        return;
        
    }
    if ([elementName isEqualToString:@"stock"]) {
        [app.stockArray addObject:stockList];
        
        stockList = nil;
    }
    else
        [stockList setValue:xmlValue forKey:elementName];
        xmlValue = nil;
    
    
}




@end