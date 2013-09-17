//
//  XMLParser.h
//  StockTrackr_iOSPortion
//
//  Created by Janelle Salisbury on 9/15/13.
//  Copyright (c) 2013 Janelle Salisbury. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "AppDelegate.h"
#import "Stock.h"

@interface XMLParser : NSObject <NSXMLParserDelegate> {
    AppDelegate *app;
    Stock *stockList;
    NSMutableString *xmlValue;
    
}

@property (nonatomic, retain) AppDelegate *app;
@property (nonatomic, retain) Stock *stockList;
@property (nonatomic, retain) NSMutableString *xmlValue;

-(id)initXMLParser;

@end
