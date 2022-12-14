USE [master]
GO
/****** Object:  Database [VegetablesManagement]    Script Date: 3/9/2022 7:37:19 PM ******/
CREATE DATABASE [VegetablesManagement]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'VegetablesManagerment', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL13.MSSQLSERVER\MSSQL\DATA\VegetablesManagerment.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'VegetablesManagerment_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL13.MSSQLSERVER\MSSQL\DATA\VegetablesManagerment_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [VegetablesManagement] SET COMPATIBILITY_LEVEL = 130
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [VegetablesManagement].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [VegetablesManagement] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [VegetablesManagement] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [VegetablesManagement] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [VegetablesManagement] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [VegetablesManagement] SET ARITHABORT OFF 
GO
ALTER DATABASE [VegetablesManagement] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [VegetablesManagement] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [VegetablesManagement] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [VegetablesManagement] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [VegetablesManagement] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [VegetablesManagement] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [VegetablesManagement] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [VegetablesManagement] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [VegetablesManagement] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [VegetablesManagement] SET  DISABLE_BROKER 
GO
ALTER DATABASE [VegetablesManagement] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [VegetablesManagement] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [VegetablesManagement] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [VegetablesManagement] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [VegetablesManagement] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [VegetablesManagement] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [VegetablesManagement] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [VegetablesManagement] SET RECOVERY FULL 
GO
ALTER DATABASE [VegetablesManagement] SET  MULTI_USER 
GO
ALTER DATABASE [VegetablesManagement] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [VegetablesManagement] SET DB_CHAINING OFF 
GO
ALTER DATABASE [VegetablesManagement] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [VegetablesManagement] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [VegetablesManagement] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'VegetablesManagement', N'ON'
GO
ALTER DATABASE [VegetablesManagement] SET QUERY_STORE = OFF
GO
USE [VegetablesManagement]
GO
ALTER DATABASE SCOPED CONFIGURATION SET MAXDOP = 0;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET MAXDOP = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET LEGACY_CARDINALITY_ESTIMATION = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET LEGACY_CARDINALITY_ESTIMATION = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET PARAMETER_SNIFFING = ON;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET PARAMETER_SNIFFING = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET QUERY_OPTIMIZER_HOTFIXES = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET QUERY_OPTIMIZER_HOTFIXES = PRIMARY;
GO
USE [VegetablesManagement]
GO
/****** Object:  Table [dbo].[tblCategory]    Script Date: 3/9/2022 7:37:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCategory](
	[categoryID] [varchar](10) NOT NULL,
	[categoryName] [nvarchar](40) NULL,
PRIMARY KEY CLUSTERED 
(
	[categoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblOrder]    Script Date: 3/9/2022 7:37:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrder](
	[orderID] [int] IDENTITY(1,1) NOT NULL,
	[orderDate] [date] NOT NULL,
	[total] [decimal](11, 4) NOT NULL,
	[userID] [varchar](32) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblOrderDetail]    Script Date: 3/9/2022 7:37:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrderDetail](
	[detailID] [int] IDENTITY(1,1) NOT NULL,
	[price] [decimal](11, 4) NOT NULL,
	[quantity] [int] NOT NULL,
	[orderID] [int] NOT NULL,
	[productID] [varchar](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[detailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblProduct]    Script Date: 3/9/2022 7:37:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblProduct](
	[productID] [varchar](10) NOT NULL,
	[productName] [nvarchar](40) NULL,
	[image] [varchar](max) NULL,
	[price] [decimal](11, 4) NULL,
	[quantity] [int] NULL,
	[categoryID] [varchar](10) NULL,
	[importDate] [date] NULL,
	[usingDate] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[productID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblRoles]    Script Date: 3/9/2022 7:37:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRoles](
	[roleID] [bit] NOT NULL,
	[roleName] [varchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblUsers]    Script Date: 3/9/2022 7:37:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUsers](
	[userID] [varchar](32) NOT NULL,
	[fullName] [nvarchar](50) NULL,
	[password] [varchar](32) NOT NULL,
	[roleID] [bit] NOT NULL,
	[address] [nvarchar](50) NULL,
	[birthday] [date] NULL,
	[phone] [varchar](15) NULL,
	[status] [bit] NOT NULL,
	[email] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (N'F0002', N'Fish')
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (N'M0001', N'Meat')
INSERT [dbo].[tblCategory] ([categoryID], [categoryName]) VALUES (N'V0003', N'Vegetable')
SET IDENTITY_INSERT [dbo].[tblOrder] ON 

INSERT [dbo].[tblOrder] ([orderID], [orderDate], [total], [userID]) VALUES (1, CAST(N'2022-03-08' AS Date), CAST(0.0000 AS Decimal(11, 4)), N'user')
INSERT [dbo].[tblOrder] ([orderID], [orderDate], [total], [userID]) VALUES (2, CAST(N'2022-03-08' AS Date), CAST(0.0000 AS Decimal(11, 4)), N'user')
INSERT [dbo].[tblOrder] ([orderID], [orderDate], [total], [userID]) VALUES (3, CAST(N'2022-03-08' AS Date), CAST(1310.0000 AS Decimal(11, 4)), N'user')
INSERT [dbo].[tblOrder] ([orderID], [orderDate], [total], [userID]) VALUES (4, CAST(N'2022-03-08' AS Date), CAST(10.0000 AS Decimal(11, 4)), N'user')
INSERT [dbo].[tblOrder] ([orderID], [orderDate], [total], [userID]) VALUES (1002, CAST(N'2022-03-09' AS Date), CAST(110.0000 AS Decimal(11, 4)), N'112779844802687210221')
INSERT [dbo].[tblOrder] ([orderID], [orderDate], [total], [userID]) VALUES (1003, CAST(N'2022-03-09' AS Date), CAST(200.5000 AS Decimal(11, 4)), N'117678152086913045807')
SET IDENTITY_INSERT [dbo].[tblOrder] OFF
SET IDENTITY_INSERT [dbo].[tblOrderDetail] ON 

INSERT [dbo].[tblOrderDetail] ([detailID], [price], [quantity], [orderID], [productID]) VALUES (1, CAST(500.0000 AS Decimal(11, 4)), 1, 1, N'A0000004')
INSERT [dbo].[tblOrderDetail] ([detailID], [price], [quantity], [orderID], [productID]) VALUES (2, CAST(10.0000 AS Decimal(11, 4)), 2, 1, N'A0000003')
INSERT [dbo].[tblOrderDetail] ([detailID], [price], [quantity], [orderID], [productID]) VALUES (3, CAST(100.0000 AS Decimal(11, 4)), 2, 1, N'A0000002')
INSERT [dbo].[tblOrderDetail] ([detailID], [price], [quantity], [orderID], [productID]) VALUES (4, CAST(100.5000 AS Decimal(11, 4)), 1, 1, N'A0000001')
INSERT [dbo].[tblOrderDetail] ([detailID], [price], [quantity], [orderID], [productID]) VALUES (5, CAST(1000.0000 AS Decimal(11, 4)), 1, 1, N'A0000005')
INSERT [dbo].[tblOrderDetail] ([detailID], [price], [quantity], [orderID], [productID]) VALUES (6, CAST(500.0000 AS Decimal(11, 4)), 1, 2, N'A0000004')
INSERT [dbo].[tblOrderDetail] ([detailID], [price], [quantity], [orderID], [productID]) VALUES (7, CAST(10.0000 AS Decimal(11, 4)), 2, 2, N'A0000003')
INSERT [dbo].[tblOrderDetail] ([detailID], [price], [quantity], [orderID], [productID]) VALUES (8, CAST(100.0000 AS Decimal(11, 4)), 1, 2, N'A0000002')
INSERT [dbo].[tblOrderDetail] ([detailID], [price], [quantity], [orderID], [productID]) VALUES (9, CAST(100.5000 AS Decimal(11, 4)), 1, 2, N'A0000001')
INSERT [dbo].[tblOrderDetail] ([detailID], [price], [quantity], [orderID], [productID]) VALUES (10, CAST(10.0000 AS Decimal(11, 4)), 1, 3, N'A0000003')
INSERT [dbo].[tblOrderDetail] ([detailID], [price], [quantity], [orderID], [productID]) VALUES (11, CAST(100.0000 AS Decimal(11, 4)), 1, 3, N'A0000002')
INSERT [dbo].[tblOrderDetail] ([detailID], [price], [quantity], [orderID], [productID]) VALUES (12, CAST(200.0000 AS Decimal(11, 4)), 1, 3, N'A0000007')
INSERT [dbo].[tblOrderDetail] ([detailID], [price], [quantity], [orderID], [productID]) VALUES (13, CAST(1000.0000 AS Decimal(11, 4)), 1, 3, N'A0000005')
INSERT [dbo].[tblOrderDetail] ([detailID], [price], [quantity], [orderID], [productID]) VALUES (14, CAST(10.0000 AS Decimal(11, 4)), 1, 4, N'A0000003')
INSERT [dbo].[tblOrderDetail] ([detailID], [price], [quantity], [orderID], [productID]) VALUES (1002, CAST(10.0000 AS Decimal(11, 4)), 1, 1002, N'A0000003')
INSERT [dbo].[tblOrderDetail] ([detailID], [price], [quantity], [orderID], [productID]) VALUES (1003, CAST(100.0000 AS Decimal(11, 4)), 1, 1002, N'A0000002')
INSERT [dbo].[tblOrderDetail] ([detailID], [price], [quantity], [orderID], [productID]) VALUES (1004, CAST(100.0000 AS Decimal(11, 4)), 1, 1003, N'A0000002')
INSERT [dbo].[tblOrderDetail] ([detailID], [price], [quantity], [orderID], [productID]) VALUES (1005, CAST(100.5000 AS Decimal(11, 4)), 1, 1003, N'A0000001')
SET IDENTITY_INSERT [dbo].[tblOrderDetail] OFF
INSERT [dbo].[tblProduct] ([productID], [productName], [image], [price], [quantity], [categoryID], [importDate], [usingDate]) VALUES (N'A0000001', N'Bacon', N'https://www.hoidaubepaau.com/wp-content/uploads/2018/11/thit-nac.jpg', CAST(100.5000 AS Decimal(11, 4)), 542, N'M0001', CAST(N'2022-03-04' AS Date), CAST(N'2022-03-30' AS Date))
INSERT [dbo].[tblProduct] ([productID], [productName], [image], [price], [quantity], [categoryID], [importDate], [usingDate]) VALUES (N'A0000002', N'Yellow Banana', N'https://previews.123rf.com/images/harlowbutler/harlowbutler1112/harlowbutler111200001/11446687-%EB%85%B8%EB%9E%80%EC%83%89-%EB%B0%94%EB%82%98%EB%82%98.jpg', CAST(100.0000 AS Decimal(11, 4)), 981, N'V0003', CAST(N'2022-03-03' AS Date), CAST(N'2022-03-16' AS Date))
INSERT [dbo].[tblProduct] ([productID], [productName], [image], [price], [quantity], [categoryID], [importDate], [usingDate]) VALUES (N'A0000003', N'Salad', N'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQGFVcC6uQJeAZC2YNa_uy9b58zj2Re6ixRew&usqp=CAU', CAST(10.0000 AS Decimal(11, 4)), 9986, N'V0003', CAST(N'2022-03-03' AS Date), CAST(N'2022-03-22' AS Date))
INSERT [dbo].[tblProduct] ([productID], [productName], [image], [price], [quantity], [categoryID], [importDate], [usingDate]) VALUES (N'A0000004', N'Tuna', N'https://everfreshseafood.co/wp-content/uploads/2020/07/Tuna-Steakwe.png', CAST(500.0000 AS Decimal(11, 4)), 990, N'F0002', CAST(N'2022-03-03' AS Date), CAST(N'2022-03-23' AS Date))
INSERT [dbo].[tblProduct] ([productID], [productName], [image], [price], [quantity], [categoryID], [importDate], [usingDate]) VALUES (N'A0000005', N'Beaf', N'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQNIHjQJ7pq1tO33RjSXReS6xY8KQ8AuqbHwgex3FzNsjAPWVsyYh2ARPsjk_iJkdQ2g7A&usqp=CAU', CAST(1000.0000 AS Decimal(11, 4)), 6, N'M0001', CAST(N'2022-03-03' AS Date), CAST(N'2022-03-30' AS Date))
INSERT [dbo].[tblProduct] ([productID], [productName], [image], [price], [quantity], [categoryID], [importDate], [usingDate]) VALUES (N'A0000007', N'Cherry', N'http://familyfruits.com.vn/wp-content/uploads/2021/01/cherry-uc.jpg', CAST(200.0000 AS Decimal(11, 4)), 996, N'V0003', CAST(N'2022-03-05' AS Date), CAST(N'2022-03-15' AS Date))
INSERT [dbo].[tblProduct] ([productID], [productName], [image], [price], [quantity], [categoryID], [importDate], [usingDate]) VALUES (N'B0000002', N'Mango', N'https://nhobonmua.com/vietnam-soft-dried-mango-22.jpg', CAST(50.0000 AS Decimal(11, 4)), 50, N'V0003', CAST(N'2022-03-09' AS Date), CAST(N'2022-03-31' AS Date))
INSERT [dbo].[tblProduct] ([productID], [productName], [image], [price], [quantity], [categoryID], [importDate], [usingDate]) VALUES (N'B000001', N'King crab', N'https://tomhumalaska.net/wp-content/uploads/2018/11/IMG_2138.jpg', CAST(300.0000 AS Decimal(11, 4)), 1, N'F0002', CAST(N'2022-03-08' AS Date), CAST(N'2022-04-09' AS Date))
INSERT [dbo].[tblProduct] ([productID], [productName], [image], [price], [quantity], [categoryID], [importDate], [usingDate]) VALUES (N'B000003', N'Orange', N'https://upload.wikimedia.org/wikipedia/commons/thumb/4/43/Ambersweet_oranges.jpg/1200px-Ambersweet_oranges.jpg', CAST(10.0000 AS Decimal(11, 4)), 1000, N'V0003', CAST(N'2022-03-09' AS Date), CAST(N'2022-03-30' AS Date))
INSERT [dbo].[tblRoles] ([roleID], [roleName]) VALUES (0, N'User')
INSERT [dbo].[tblRoles] ([roleID], [roleName]) VALUES (1, N'Admin')
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID], [address], [birthday], [phone], [status], [email]) VALUES (N'112779844802687210221', N'hiddenwory@gmail.com', N'yhj2RsPilByvaE9', 0, NULL, NULL, NULL, 1, N'hiddenwory@gmail.com')
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID], [address], [birthday], [phone], [status], [email]) VALUES (N'117678152086913045807', N'hidden2792001@gmail.com', N'a4N68zMf0CVd2gr', 0, NULL, NULL, NULL, 1, N'hidden2792001@gmail.com')
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID], [address], [birthday], [phone], [status], [email]) VALUES (N'admin', N'admin', N'admin', 1, NULL, CAST(N'1999-01-01' AS Date), N'0123456789', 1, NULL)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID], [address], [birthday], [phone], [status], [email]) VALUES (N'admin01', N'admin01', N'admin', 1, N'Quan 9, TPHCM', CAST(N'1999-02-01' AS Date), N'0123456789', 1, NULL)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID], [address], [birthday], [phone], [status], [email]) VALUES (N'Customer01', N'Nguyen Van A', N'123456', 0, N'Quan 7, TPHCM', CAST(N'1999-03-02' AS Date), N'0987654321', 1, NULL)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID], [address], [birthday], [phone], [status], [email]) VALUES (N'User', N'Nguyen Van B', N'123456', 0, N'Thu Dau Mot, Binh Duong', CAST(N'2001-09-27' AS Date), N'0913243434', 1, NULL)
ALTER TABLE [dbo].[tblOrder]  WITH CHECK ADD FOREIGN KEY([userID])
REFERENCES [dbo].[tblUsers] ([userID])
GO
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD FOREIGN KEY([orderID])
REFERENCES [dbo].[tblOrder] ([orderID])
GO
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD FOREIGN KEY([productID])
REFERENCES [dbo].[tblProduct] ([productID])
GO
ALTER TABLE [dbo].[tblProduct]  WITH CHECK ADD FOREIGN KEY([categoryID])
REFERENCES [dbo].[tblCategory] ([categoryID])
GO
ALTER TABLE [dbo].[tblUsers]  WITH CHECK ADD FOREIGN KEY([roleID])
REFERENCES [dbo].[tblRoles] ([roleID])
GO
USE [master]
GO
ALTER DATABASE [VegetablesManagement] SET  READ_WRITE 
GO
